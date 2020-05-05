package com.example.test.ui;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.test.R;
import com.example.test.databinding.FragmentGameBinding;
import com.example.test.game.Board;
import com.example.test.game.Game;
import com.example.test.game.Player;

public class GameFragment extends Fragment {


    private Button[][] buttons = new Button[3][3];
    private Game game;
    private FragmentActivity activity;
    private FragmentGameBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game,
                container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        game = new Game();
        buildGameField();
        game.start();
    }

    private void buildGameField() {
        Board[][] field = game.getField();
        for (int i = 0; i < field.length; i++) {
            TableRow row = new TableRow(activity);
            for (int j = 0; j < field[i].length; j++) {
                Button button = new Button(activity);
                buttons[i][j] = button;
                button.setOnClickListener(new Listener(i, j));
                row.addView(button, new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                row.setGravity(Gravity.CENTER_HORIZONTAL);
                button.setWidth(320);
                button.setHeight(320);
            }

            binding.table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
        binding.table.setGravity(Gravity.CENTER);
    }

    public class Listener implements View.OnClickListener {
        private int x;
        private int y;

        Listener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            Button button = (Button) view;
            Game currentGame = game;
            Player player = currentGame.getCurrentActivePlayer();
            if (makeTurn(x, y)) {
                button.setText(player.getName());
            }
            Player winner = currentGame.checkWinner();
            if (winner != null) {
                gameOver(winner);
            }
            if (currentGame.isFieldFilled()) {
                gameOver();
            }
        }

        private void gameOver(Player player) {
            CharSequence text = "Player \"" + player.getName() + "\" won!";
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
            game.reset();
            refresh();
        }

        private void gameOver() {
            CharSequence text = "Draw";
            Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
            game.reset();
            refresh();
        }

        private boolean makeTurn(int x, int y) {
            return game.makeTurn(x, y);
        }

        private void refresh() {
            Board[][] field = game.getField();

            for (int i = 0, len = field.length; i < len; i++) {
                for (int j = 0, len2 = field[i].length; j < len2; j++) {
                    if (field[i][j].getPlayer() == null) {
                        buttons[i][j].setText("");
                    } else {
                        buttons[i][j].setText(field[i][j].getPlayer().getName());
                    }
                }
            }
        }
    }
}

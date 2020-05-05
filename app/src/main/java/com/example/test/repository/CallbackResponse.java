package com.example.test.repository;

import com.example.test.model.Response;

public interface CallbackResponse {
    void response(Response data);

    void error(Throwable throwable);
}

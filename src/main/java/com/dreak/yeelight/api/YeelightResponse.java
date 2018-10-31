package com.dreak.yeelight.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class YeelightResponse {

    private Integer id;
    private String[] result;
    private Error error;


    public static YeelightResponse of(String response) throws IOException {
        return new ObjectMapper().readValue(response, YeelightResponse.class);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getResult() {
        return result;
    }

    public void setResult(String[] result) {
        this.result = result;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    private static class Error {
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}

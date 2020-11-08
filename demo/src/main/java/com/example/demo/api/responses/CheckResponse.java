package com.example.demo.api.responses;

import java.util.Objects;

/**
 * CheckResponse
 */
public class CheckResponse {

    private boolean response;


    public CheckResponse() {
    }

    public CheckResponse(boolean response) {
        this.response = response;
    }

    public boolean isResponse() {
        return this.response;
    }

    public boolean getResponse() {
        return this.response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public CheckResponse response(boolean response) {
        this.response = response;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CheckResponse)) {
            return false;
        }
        CheckResponse checkResponse = (CheckResponse) o;
        return response == checkResponse.response;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(response);
    }

    @Override
    public String toString() {
        return "{" +
            " response='" + isResponse() + "'" +
            "}";
    }

}
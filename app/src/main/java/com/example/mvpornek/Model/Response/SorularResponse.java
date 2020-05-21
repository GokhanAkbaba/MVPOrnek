package com.example.mvpornek.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SorularResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("question")
    @Expose
    private SorularResponse question;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SorularResponse getQuestion() {
        return question;
    }

    public void setQuestion(SorularResponse question) {
        this.question = question;
    }

}

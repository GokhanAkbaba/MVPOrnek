package com.birinesor.mvpornek.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CevapKazancResponse {
        @SerializedName("error")
        @Expose
        private Boolean error;
        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("message")
        @Expose
        private String message;

        public Boolean getError() {
            return error;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public CevapKazancResponse(Boolean error, Integer code, String message) {
            this.error = error;
            this.code = code;
            this.message = message;
        }
    }


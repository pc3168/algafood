package com.algworks.com.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.LocalDateTime;

@JsonInclude(Include.NON_NULL)
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private LocalDateTime timestamp;


    public Problem(Integer status, String type, String title, String detail, String userMessage, LocalDateTime timestamp) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.timestamp = timestamp;
    }


    public Integer getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getUserMessage(){
        return userMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "status=" + status +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    static class ProblemBuilder{

        private Integer status;
        private String type;
        private String title;
        private String detail;
        private String userMessage;
        private LocalDateTime timestamp;

        private Problem problem;

        public static ProblemBuilder builder(){
            return new ProblemBuilder();
        }

        public ProblemBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        public ProblemBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ProblemBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ProblemBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public ProblemBuilder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public ProblemBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ProblemBuilder problem(Problem problem) {
            this.problem = problem;
            return this;
        }

        public Problem build(){
            return new Problem(status, type, title, detail, userMessage, timestamp);
        }
    }
}

package com.algworks.com.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time. OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Problem {

    private Integer status;
    private  OffsetDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Field> fields;


    public Problem(Integer status,  OffsetDateTime timestamp, String type, String title, String detail, String userMessage, List<Field> fields) {
        this.status = status;
        this.timestamp = timestamp;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.fields = fields;
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

    public  OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public List<Field> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", fields=" + fields +
                '}';
    }

    static class ProblemBuilder{

        private Integer status;
        private  OffsetDateTime timestamp;
        private String type;
        private String title;
        private String detail;
        private String userMessage;
        private List<Field> fields;


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

        public ProblemBuilder timestamp( OffsetDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ProblemBuilder fields(List<Field> fields) {
            this.fields = fields;
            return this;
        }

        public ProblemBuilder problem(Problem problem) {
            this.problem = problem;
            return this;
        }

        public Problem build(){
            return new Problem(status, timestamp, type, title, detail, userMessage, fields);
        }
    }
}

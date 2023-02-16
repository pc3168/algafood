package com.algworks.com.algafood.api.exceptionhandler;

public class Field {

    private String name;
    private String userMessage;

    public Field(String name, String userMessage) {
        this.name = name;
        this.userMessage = userMessage;
    }

    public String getName() {
        return name;
    }

    public String getUserMessage() {
        return userMessage;
    }



    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", userMessage='" + userMessage + '\'' +
                '}';
    }

    static class FieldBuilder{
        private String name;
        private String userMessage;


        private Field field;

        public static FieldBuilder builder() {
            return new FieldBuilder();
        }

        public Field build(){
            return new Field(name, userMessage);
        }

        public FieldBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FieldBuilder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public FieldBuilder field(Field field) {
            this.field = field;
            return this;
        }
    }


}

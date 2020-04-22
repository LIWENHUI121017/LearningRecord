package com.day06;

public class CopyException extends Exception{
        public CopyException(){
            super();
        }

        public CopyException(String message,Throwable cause){
            super(message,cause);
        }

        public CopyException(String message){
            super(message);
        }

        public CopyException(Throwable cause){
            super(cause);
        }
}
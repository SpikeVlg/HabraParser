package com.github.spikevlg.habraparser;

public class HabraParserException extends Exception {
    public HabraParserException(String msg){
        super(msg);
    }
    public HabraParserException(Exception ex){
        super(ex);
    }
}

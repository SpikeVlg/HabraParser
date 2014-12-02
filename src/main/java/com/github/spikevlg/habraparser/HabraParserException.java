package com.github.spikevlg.habraparser;

/**
 * These root exception for habraparser.
 */
public class HabraParserException extends RuntimeException {
    /**
     * Constructor of GrabException that
     * @param msg - an error message
     */
    public HabraParserException(String msg){
        super(msg);
    }
    /**
     * Constructor for exception caused other exception.
     * @param ex - a caused exception.
     */
    public HabraParserException(Exception ex){
        super(ex);
    }
}

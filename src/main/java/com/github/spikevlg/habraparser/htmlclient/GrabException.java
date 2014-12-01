
package com.github.spikevlg.habraparser.htmlclient;

import com.github.spikevlg.habraparser.HabraParserException;

/**
 * These exception can be thrown if interface Grab got a error.
 */
public class GrabException extends HabraParserException {
    /**
     * Constructor of GrabException that
     * @param msg - an error message
     */
    public GrabException(String msg){
        super(msg);
    }

    /**
     * Constructor for exception caused other exception.
     * @param ex - a caused exception.
     */
    public GrabException(Exception ex){
        super(ex);
    }
}

package com.github.spikevlg;

public class GrabException extends Exception {
    public GrabException(String msg){
        super(msg);
    }

    public GrabException(Exception ex){
        super(ex);
    }
}
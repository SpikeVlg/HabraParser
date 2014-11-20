
package com.github.spikevlg.habraparser;

public class GrabException extends Exception {
    public GrabException(String msg){
        super(msg);
    }

    public GrabException(Exception ex){
        super(ex);
    }
}
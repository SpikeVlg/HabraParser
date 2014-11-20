
package com.github.spikevlg.habraparser.htmlclient;

public class GrabException extends Exception {
    public GrabException(String msg){
        super(msg);
    }

    public GrabException(Exception ex){
        super(ex);
    }
}
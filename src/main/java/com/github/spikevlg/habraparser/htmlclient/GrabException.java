
package com.github.spikevlg.habraparser.htmlclient;

import com.github.spikevlg.habraparser.HabraParserException;

public class GrabException extends HabraParserException {
    public GrabException(String msg){
        super(msg);
    }

    public GrabException(Exception ex){
        super(ex);
    }
}
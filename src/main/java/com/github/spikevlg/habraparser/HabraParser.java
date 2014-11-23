package com.github.spikevlg.habraparser;

import com.github.spikevlg.habraparser.contentprovider.InjectLogger;
import com.github.spikevlg.habraparser.habraparser.HabraParsable;
import com.github.spikevlg.habraparser.htmlclient.Grab;
import com.google.inject.Inject;
import org.slf4j.Logger;

public class HabraParser {
    @InjectLogger
    private Logger logger;
    private Grab grab;
    private HabraParsable htmlParser;

    @Inject
    public HabraParser(Grab grab, HabraParsable htmlParser){
        this.grab = grab;
        this.htmlParser = htmlParser;
    }

    public HabraItem parse(int postID) throws Exception{
        String page = grab.go("http://habrahabr.ru/post/" + postID);
        return htmlParser.parsePost(postID, page);
    }
}

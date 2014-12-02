package com.github.spikevlg.habraparser;

import com.github.spikevlg.habraparser.contentprovider.InjectLogger;
import com.github.spikevlg.habraparser.htmlparser.HtmlHabraParser;
import com.github.spikevlg.habraparser.htmlclient.Grab;
import com.google.inject.Inject;
import org.slf4j.Logger;

/**
 * Class represent main logic for download and parse article from habrahabr sites.
 */
public class HabraParser {
    /**
     * A logger object.
     */
    @InjectLogger
    private Logger logger;
    /**
     * Downloader urls.
     */
    private Grab grab;
    /**
     * Parser html pages.
     */
    private HtmlHabraParser htmlParser;

    @Inject
    public HabraParser(Grab grab, HtmlHabraParser htmlParser){
        this.grab = grab;
        this.htmlParser = htmlParser;
    }

    /**
     * Parses main indicators from article by post id.
     * @param postID - post id
     * @return parsed article object.
     */
    public HabraItem parse(int postID){
        String page = grab.go("http://habrahabr.ru/post/" + postID);
        return htmlParser.parsePost(postID, page);
    }

    /**
     * Parses last post id from main page of site habrahabr.
     * @return post id with maximum value.
     */
    public int getLastPostId(){
        String page = grab.go("http://habrahabr.ru/");
        return htmlParser.getLastPostId(page);
    }
}

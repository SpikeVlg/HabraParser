package com.github.spikevlg.habraparser;

import com.github.spikevlg.habraparser.htmlparser.HtmlHabraParser;
import com.github.spikevlg.habraparser.htmlclient.Grab;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Tests for class HabraParser.
 */
public class HabraParserTest {
    private Grab grab;
    private HtmlHabraParser htmlParser;
    private HabraParser habraParser ;

    private static final String MAIN_PAGE_BODY = "html habrahabr main page body";
    private static final int EXPECTED_LAST_POST_ID = 12345;


    /**
     * Creates mock objects.
     */
    @Before
    public void setUp(){
        grab = mock(Grab.class);
        htmlParser = mock(HtmlHabraParser.class);
        habraParser = new HabraParser(grab, htmlParser);
    }

    /**
     * Tests getLastPageId method.
     */
    @Test
    public void getLastPageId(){
        when(grab.go("http://habrahabr.ru/")).thenReturn(MAIN_PAGE_BODY);
        when(htmlParser.getLastPostId(MAIN_PAGE_BODY)).thenReturn(EXPECTED_LAST_POST_ID);
        assertEquals(EXPECTED_LAST_POST_ID, habraParser.getLastPostId());
    }

    /**
     * Tests parsePost method.
     */
    @Test
    public void parsePost() {
        HabraItem item = new HabraItem();
        item.setId(EXPECTED_LAST_POST_ID);
        item.setTitle("some title");

        when(grab.go("http://habrahabr.ru/post/" + EXPECTED_LAST_POST_ID)).thenReturn(MAIN_PAGE_BODY);
        when(htmlParser.parsePost(EXPECTED_LAST_POST_ID, MAIN_PAGE_BODY)).thenReturn(item);
        assertEquals(item, habraParser.parse(EXPECTED_LAST_POST_ID));
    }
}

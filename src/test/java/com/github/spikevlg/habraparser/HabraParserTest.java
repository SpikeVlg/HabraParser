package com.github.spikevlg.habraparser;

import com.github.spikevlg.habraparser.habraparser.HabraParsable;
import com.github.spikevlg.habraparser.htmlclient.Grab;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class HabraParserTest {
    private Grab grab;
    private HabraParsable htmlParser;
    private HabraParser habraParser ;

    @Before
    public void beforeTest(){
        grab = mock(Grab.class);
        htmlParser = mock(HabraParsable.class);
        habraParser = new HabraParser(grab, htmlParser);
    }

    @Test
    public void getLastPageId(){
        String MAIN_PAGE_BODY = "html habrahabr main page body";
        int EXPECTED_LAST_POST_ID = 12345;
        when(grab.go("http://habrahabr.ru/")).thenReturn(MAIN_PAGE_BODY);
        when(htmlParser.getLastPostId(MAIN_PAGE_BODY)).thenReturn(EXPECTED_LAST_POST_ID);
        assertEquals(habraParser.getLastPostId(), EXPECTED_LAST_POST_ID);
    }

}

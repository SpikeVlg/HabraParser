package com.github.spikevlg.habraparser;

import com.github.spikevlg.habraparser.habraparser.HabraParsable;
import com.github.spikevlg.habraparser.htmlclient.Grab;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class HabraParserTest {

    @Test
    void getLastPageId(){
        String MAIN_PAGE_BODY = "html habrahabr main page body";
        int EXPECTED_LAST_POST_ID = 12345;
        Grab grab = mock(Grab.class);
        HabraParsable htmlParser = mock(HabraParsable.class);
        when(grab.go("http://habrahabr.ru/")).thenReturn(MAIN_PAGE_BODY);
        when(htmlParser.getLastPostId(MAIN_PAGE_BODY)).thenReturn(EXPECTED_LAST_POST_ID);

        HabraParser habraParser = new HabraParser(grab, htmlParser);
        assertEquals(habraParser.getLastPostId(), EXPECTED_LAST_POST_ID);
    }

}

package com.github.spikevlg.habraparser.habraparser;


import com.github.spikevlg.habraparser.HabraItem;
import com.github.spikevlg.habraparser.HabraParserException;
import com.github.spikevlg.habraparser.contentprovider.ContentProvider;
import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class HtmlCleanerHabraParserTest {
    @Inject
    private HtmlCleanerHabraParser htmlParser;

    @Before
    public void setUp() throws Exception {
        Guice.createInjector(new ContentProvider()).injectMembers(this);
    }

    private static final String MAIN_PAGE_WITH_POSTS = "<html><body>"
            + " <div @class=\"posts_list\"><div>"
            + " <div @id=\"post_14\"></div>"
            + " <div @id=\"post_1234\"></div>"
            + " <div @id=\"post_784\"></div>"
            + "</div></div></body></html>";

    @Test
    public void getLastPostIdSuccess(){
        assertEquals(1234, htmlParser.getLastPostId(MAIN_PAGE_WITH_POSTS));
    }

    private static final String MAIN_PAGE_WITHOUT_POSTS = "<html><body></body></html>";

    @Test(expected=HabraParserException.class)
    public void getLastPostIdFail() {
        htmlParser.getLastPostId(MAIN_PAGE_WITHOUT_POSTS);
    }

    private static final int PAGE_ID = 56789;
    private static final String PAGE_TITLE = "TITLE";
    private static final String PAGE_AUTHOR = "AUTHOR";
    private static final int PAGE_VIEWS = 12345;
    private static final int PAGE_STARS = 23456;
    private static final int PAGE_COMMENTS = 34567;
    private static final int PAGE_SCORE = 45678;
    private static final String POST_PAGE = "<html><body>"
            + "<h1><span @class=\"post_title\">" + PAGE_TITLE + "</span></h1>" // title
            + "<div @class=\"author\"><a>" + PAGE_AUTHOR + "</a></div>"
            + "<div @class=\"pageviews\">" + PAGE_VIEWS + "</div>"
            + "<div @class=\"favs_count\">" + PAGE_STARS + "</div>"
            + "<span @id=\"comments_count\">" + PAGE_COMMENTS + "</span>"
            + "<span @class=\"score\">+" + PAGE_SCORE + "</span>"
            + "<div @class=\"hubs\"><a>HUB1</a></div>"
            + "<div @class=\"hubs\"><a>HUB2</a></div>"
            + "<ul @class=\"tags\"><li><a>TAG1</a>/<li></ul>"
            + "<ul @class=\"tags\"><li><a>TAG2</a>/<li></ul>"
            + "</body></html>";
    @Test
    public void parsePost(){
        HabraItem habraItem = htmlParser.parsePost(PAGE_ID, POST_PAGE);
        assertEquals(PAGE_ID, habraItem.getId());
        assertEquals(PAGE_TITLE, habraItem.getTitle());
        assertEquals(PAGE_AUTHOR, habraItem.getAuthor());
        assertEquals(PAGE_COMMENTS, habraItem.getCountComments());
        assertEquals(PAGE_STARS, habraItem.getCountStars());
        assertEquals(PAGE_SCORE, habraItem.getScore(), 0.0001);
        assertEquals(false, habraItem.isTranslate());

        List<String> listHubs = Arrays.asList("HUB1", "HUB2");
        List<String> listTags = Arrays.asList("TAG1", "TAG2");
        assertThat(habraItem.getListHubs(), is(listHubs));
        assertThat(habraItem.getListTags(), is(listTags));
    }
}

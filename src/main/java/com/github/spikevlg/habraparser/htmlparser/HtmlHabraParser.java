
package com.github.spikevlg.habraparser.htmlparser;

import com.github.spikevlg.habraparser.HabraItem;

/**
 * Interface parses html pages from habrahabr sites.
 */
public interface HtmlHabraParser {
    /**
     * Parses main indicators from post page.
     * @param postId - post id
     * @param postPage - page body of article
     * @return parsed structure
     */
    HabraItem parsePost(int postId, String postPage);

    /**
     * Returnes last post ID/
     * @param mainPage - the body of main page with list articles.
     * @return last post ID
     */
    int getLastPostId(String mainPage);
}
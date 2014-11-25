
package com.github.spikevlg.habraparser.htmlparser;

import com.github.spikevlg.habraparser.HabraItem;

public interface HtmlHabraParser {
    HabraItem parsePost(int postId, String postPage);
    int getLastPostId(String mainPage);
}
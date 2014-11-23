
package com.github.spikevlg.habraparser.habraparser;

import com.github.spikevlg.habraparser.HabraItem;

public interface HabraParsable {
    HabraItem parsePost(int postId, String postPage);
    int getLastPostId(String mainPage);
}
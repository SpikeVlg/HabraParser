
package com.github.spikevlg.habraparser.habraparser;

import com.github.spikevlg.habraparser.HabraItem;
import com.github.spikevlg.habraparser.HabraParserException;

public interface HabraParsable {
    public HabraItem parsePost(String postPage) throws HabraParserException;
}
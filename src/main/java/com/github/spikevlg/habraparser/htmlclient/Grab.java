
package com.github.spikevlg.habraparser.htmlclient;

/**
 * Interface for grab page body from web-sites.
 */
public interface Grab {
	/**
	 * Returns page body by given url.
	 * @param url for request
	 * @return the page body
	 * @throws GrabException may throw exception if can't get page
	 */
	public String go(String url) throws GrabException;
}
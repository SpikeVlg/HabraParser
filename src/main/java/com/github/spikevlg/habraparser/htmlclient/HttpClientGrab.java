package com.github.spikevlg.habraparser.htmlclient;

import com.google.inject.Inject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Implementation Grab interface.
 */
public class HttpClientGrab implements Grab {
    /**
     * A logger object.
     */
    Logger logger = LoggerFactory.getLogger(HttpClientGrab.class);;

    /**
     * A CloseableHttpClient object from apache.http.client.
     */
    CloseableHttpClient httpClient;

    /**
     * Creates the {@link HttpClientGrab}
     * @param httpClient - an object apache.http.client
     */
    @Inject
    public HttpClientGrab(CloseableHttpClient httpClient){
        this.httpClient = httpClient;
    }

    /**
     * Returns page body by given url.
     * @param url for request
     * @return the page body
     * @throws GrabException may throw exception if can't get page
     */
    @Override
    public String go(String url) throws GrabException{
        logger.debug(url);
        HttpGet httpget = new HttpGet(url);

        try {
            HttpResponse httpReponse = httpClient.execute(httpget);
            HttpEntity entity = httpReponse.getEntity();
            String pageBody = EntityUtils.toString(entity);
            return pageBody;
        } catch (IOException ex){
            logger.error("go: url = %s, ex = %s", url, ex.getMessage());
            throw new GrabException(ex);
        }
    }
}
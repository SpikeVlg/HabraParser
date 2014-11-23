package com.github.spikevlg.habraparser.htmlclient;

import com.github.spikevlg.habraparser.contentprovider.InjectLogger;
import com.google.inject.Inject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpClientGrab implements Grab {
    @InjectLogger
    Logger logger;

    CloseableHttpClient httpClient;

    @Inject
    public HttpClientGrab(CloseableHttpClient httpClient){
        this.httpClient = httpClient;
    }

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
package com.github.spikevlg.habraparser;

import java.io.IOException;
import com.google.inject.Inject;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;

class HttpClientGrab implements Grab {

    CloseableHttpClient httpClient;

    @Inject
    public HttpClientGrab(CloseableHttpClient httpClient){
        this.httpClient = httpClient;
    }

    @Override
    public String go(String url) throws GrabException{
        HttpGet httpget = new HttpGet(url);

        try {
            HttpResponse httpReponse = httpClient.execute(httpget);
            HttpEntity entity = httpReponse.getEntity();
            String pageBody = EntityUtils.toString(entity);
            return pageBody;
        } catch (IOException ex){
            throw new GrabException(ex);
        }
    }
}
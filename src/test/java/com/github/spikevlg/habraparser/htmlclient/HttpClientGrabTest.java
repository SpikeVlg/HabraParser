package com.github.spikevlg.habraparser.htmlclient;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Class for test HttpClientGrab.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({EntityUtils.class, HttpClientGrab.class})
public class HttpClientGrabTest {

    /**
     * Success test for download some page.
     * @throws Exception
     */
    @Test
    public void goTestSuccess() throws Exception {
        final String URL = "http://habrahabr.ru";
        final String PAGE_BODY = "PAGE_BODY";

        // prepare steps
        HttpGet httpGet = PowerMockito.mock(HttpGet.class);
        whenNew(HttpGet.class).withArguments(URL).thenReturn(httpGet);

        CloseableHttpClient httpClient = mock(CloseableHttpClient.class);
        CloseableHttpResponse httpResponse = mock(CloseableHttpResponse.class);
        when(httpClient.execute(httpGet)).thenReturn(httpResponse);

        HttpEntity httpEntity = mock(HttpEntity.class);
        when(httpResponse.getEntity()).thenReturn(httpEntity);

        mockStatic(EntityUtils.class);
        when(EntityUtils.toString(httpEntity)).thenReturn(PAGE_BODY);

        // test go method
        HttpClientGrab httpClientGrab = new HttpClientGrab(httpClient);
        assertEquals(PAGE_BODY, httpClientGrab.go(URL));
    }

    /**
     * Test check exception behavior caused by wrong url.
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void goTestWrongUrl() throws Exception {
        CloseableHttpClient httpClient = mock(CloseableHttpClient.class);
        HttpClientGrab httpClientGrab = new HttpClientGrab(httpClient);
        httpClientGrab.go("wrong url");
    }
}

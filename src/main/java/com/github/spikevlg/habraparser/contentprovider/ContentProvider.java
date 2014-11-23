
package com.github.spikevlg.habraparser.contentprovider;

import com.github.spikevlg.habraparser.habraparser.HabraParsable;
import com.github.spikevlg.habraparser.habraparser.HtmlCleanerHabraParser;
import com.github.spikevlg.habraparser.htmlclient.Grab;
import com.github.spikevlg.habraparser.htmlclient.HttpClientGrab;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.matcher.Matchers;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;


public class ContentProvider extends AbstractModule {
    @Override
    protected void configure() {
        bind(HabraParsable.class).to(HtmlCleanerHabraParser.class);
        bind(Grab.class).to(HttpClientGrab.class);
        bindListener(Matchers.any(), new Slf4jTypeListener());
    }

    @Provides
    CloseableHttpClient provideCloseableHttpClient(){
        String proxyHost = System.getProperty("http.proxyHost");
        String stringProxyPort = System.getProperty("http.proxyPort");
        
        if (proxyHost != null && stringProxyPort != null) {
            int proxyPort = new Integer(stringProxyPort);

            String proxyUser = System.getProperty("http.proxyUser");
            String proxyPassword = System.getProperty("http.proxyPassword");
            CredentialsProvider credsProvider = new BasicCredentialsProvider();

            if (proxyUser != null && proxyPassword != null){
                UsernamePasswordCredentials creds = new UsernamePasswordCredentials(proxyUser, proxyPassword);
                credsProvider.setCredentials( new AuthScope(proxyHost, proxyPort), creds );
            }
            else{
                credsProvider.setCredentials( new AuthScope(proxyHost, proxyPort), null);
            }

            return  HttpClientBuilder.create()
                    .useSystemProperties()
                    .setProxy(new HttpHost(proxyHost, proxyPort))
                    .setDefaultCredentialsProvider(credsProvider)
                    .setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy())
                    .build();
        } else {
            return HttpClientBuilder.create().build();
        }
    }

    @Provides
    DomSerializer provideDomSerializer(){
        return new DomSerializer(new CleanerProperties());
    }

    @Provides
    XPath provideXPath(){
        return XPathFactory.newInstance().newXPath();
    }

}
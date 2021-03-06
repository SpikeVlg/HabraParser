
package com.github.spikevlg.habraparser.contentprovider;

import com.github.spikevlg.habraparser.htmlparser.HtmlHabraParser;
import com.github.spikevlg.habraparser.htmlparser.HtmlCleanerHabraParser;
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

/**
 * Class configure guice dependency injection.
 */
public class ContentProvider extends AbstractModule {
    /**
     * Configure bindings.
     */
    @Override
    protected void configure() {
        bind(HtmlHabraParser.class).to(HtmlCleanerHabraParser.class);
        bind(Grab.class).to(HttpClientGrab.class);
        bindListener(Matchers.any(), new Slf4jTypeListener());
    }

    /**
     * Creates special http client object.
     * Can be configure with proxy by system property mechanism.
     * Available next system propeties:
     * http.proxyHost
     * http.proxyPost
     * http.proxyUser
     * http.proxyPassword
     * @return object of CloseableHttpClient class.
     */
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

    /**
     * Creates dom serializer object.
     * @return object of DomSerializer class.
     */
    @Provides
    DomSerializer provideDomSerializer(){
        return new DomSerializer(new CleanerProperties());
    }

    /**
     * Creates XPath objects by builder.
     * @return object of XPath class.
     */
    @Provides
    XPath provideXPath(){
        return XPathFactory.newInstance().newXPath();
    }

}
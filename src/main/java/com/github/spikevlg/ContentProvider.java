
package com.github.spikevlg;

public class ContentProvider extends AbstractModule {
    @Override
    protected void configure() {
        bind(Grab.class).to(HttpClientGrab.class);
        bind(HttpClientGrab.class).toProvider();
    }

    @Provides
    CloseableHttpClient provideCloseableHttpClient(){
        String proxyHost = System.getProperty("http.proxyHost");
        String stringProxyPort = System.getProperty("http.proxyPort");

        if (proxyHost != null && stringProxyPort != null) {
            int proxyPort = new Integer(stringProxyPort);
        } else {
            return HttpClientBuilder.create().build();
        }

        

        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
                System.getProperty("http.proxyUser")
                , System.getProperty("http.proxyPassword"));



        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials( new AuthScope(proxyHost, proxyPort), creds );

        httpClient = HttpClientBuilder.create()
                .useSystemProperties()
                .setProxy(new HttpHost(proxyHost, proxyPort))
                .setDefaultCredentialsProvider(credsProvider)
                .setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy())
                .build();
        return httpClient;
    }
}
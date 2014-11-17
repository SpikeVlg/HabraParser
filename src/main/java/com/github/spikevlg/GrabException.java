
package com.github.spikevlg;

public class GrabException extends Exception {
	public GrabException(String msg){
		super(msg);
	}

	@Override
    protected void configure() {
        //bind(CloseableHttpClient.class).annotatedWith(Names.named("nonproxy")).toProvider();

        //bind()
        //install(new );
//        install(new FactoryModuleBuilder()
//                        .implement(CloseableHttpClient.class, CloseableHttpClient.class)
//                        .build(HttpClientFactory.class)
//        );
    }

    @Provides @Named("nonproxy")
    CloseableHttpClient provideCloseableHttpClient(){
        System.out.println("=============1111111111111111");
        return HttpClientBuilder.create().build();
    }

    @Provides @Named("proxy")
    CloseableHttpClient provideProxyCloseableHttpClient(){
        System.out.println("=============22222222");
        return HttpClientBuilder.create().build();
    }
}
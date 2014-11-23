package com.github.spikevlg.habraparser;

import com.github.spikevlg.habraparser.contentprovider.ContentProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Application {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new ContentProvider());
        HabraParser hp = injector.getInstance(HabraParser.class);
        int lastPostId = hp.getLastPostId();
        hp.parse(lastPostId);
    }
}
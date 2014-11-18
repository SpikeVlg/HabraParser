package com.github.spikevlg;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Application {
    public static void main(String[] args) throws Exception {
        Injector injector = Guice.createInjector(new ContentProvider());
        Grab grab = injector.getInstance(Grab.class);
        String page = grab.go("http://www.ya.ru/");
        System.out.println(page);
    }
}
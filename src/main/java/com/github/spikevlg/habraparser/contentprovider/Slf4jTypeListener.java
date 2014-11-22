package com.github.spikevlg.habraparser.contentprovider;

import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class Slf4jTypeListener implements TypeListener {
    @Override
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        Class<?> clazz = typeLiteral.getRawType();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType() == Logger.class &&
                        field.isAnnotationPresent(InjectLogger.class)) {
                    typeEncounter.register(new Slf4jMembersInjector<I>(field));
                }
            }
            clazz = clazz.getSuperclass();
        }
    }
}

class Slf4jMembersInjector<T> implements MembersInjector<T> {
    private final Field field;
    private final Logger logger;

    Slf4jMembersInjector(Field field) {
        this.field = field;
        this.logger = LoggerFactory.getLogger(field.getDeclaringClass());
        field.setAccessible(true);
    }

    @Override
    public void injectMembers(T t) {
        try {
            field.set(t, logger);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

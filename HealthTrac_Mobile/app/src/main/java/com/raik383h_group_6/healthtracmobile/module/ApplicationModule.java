package com.raik383h_group_6.healthtracmobile.module;

import com.google.inject.AbstractModule;
import com.raik383h_group_6.healthtracmobile.service.IOAuthServiceAdapterFactory;
import com.raik383h_group_6.healthtracmobile.service.ScribeOAuthServiceAdapterFactory;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IOAuthServiceAdapterFactory.class).to(ScribeOAuthServiceAdapterFactory.class);
    }
}

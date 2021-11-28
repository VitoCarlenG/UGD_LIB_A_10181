package com.aditiaryohandoko.ugd_lib_a_10181.di;

import android.content.Context;

import com.aditiaryohandoko.ugd_lib_a_10181.repositories.MainRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public MainRepository provideMainRepository(
            @ApplicationContext Context context
    ) {
        return new MainRepository(context);
    }
}

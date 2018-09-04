package com.chocfun.aclient.testapp.dagger2.di;

import com.chocfun.aclient.testapp.dagger2.Dagger2Contracts;
import com.chocfun.aclient.testapp.dagger2.Dagger2Presenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private Dagger2Contracts.View mView;

    public MainModule(Dagger2Contracts.View view) {
        mView = view;
    }

    @Provides
    public Dagger2Contracts.View provideView() {
        return this.mView;
    }

    @Provides
    public Dagger2Contracts.Presenter providePresenter() {
        return new Dagger2Presenter(provideView());
    }
}

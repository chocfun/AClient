package com.chocfun.aclient.testapp.dagger2.di;

import com.chocfun.aclient.testapp.dagger2.Dagger2Activity;

import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(Dagger2Activity activity);
}

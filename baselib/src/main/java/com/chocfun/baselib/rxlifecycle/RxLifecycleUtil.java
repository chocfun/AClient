package com.chocfun.baselib.rxlifecycle;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.BehaviorSubject;

public class RxLifecycleUtil {
    public static <T, L> ObservableTransformer<T, T> bindUtil(Class<T> streamType,
                                                              BehaviorSubject<L> lifecycleBehaviorSubject,
                                                              final L lifecycle) {
        final Observable<L> filter = lifecycleBehaviorSubject.filter(new Predicate<L>() {
            @Override
            public boolean test(L l) throws Exception {
                return lifecycle.equals(l);
            }
        });

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.takeUntil(filter);
            }
        };
    }
}

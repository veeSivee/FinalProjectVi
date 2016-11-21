package com.iak.vi.finalprojectvi;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by taufiqotulfaidah on 11/21/16.
 */

public abstract class UseCase {

    private Subscription subscription = Subscriptions.empty();

    protected abstract Observable buildUseCaseObservable();

    public void execute(Subscriber useCaseSubcriber) {
        this.subscription = this.buildUseCaseObservable()
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(useCaseSubcriber);
    }

    public void unsubscribe() {
        subscription.unsubscribe();
    }
}

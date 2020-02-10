package com.jhoanes.apps.android.githubissues.utils

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RxUtil private constructor() {

    companion object {
        fun <T> applySchedulers(): Observable.Transformer<T, T> =
            Observable.Transformer { observable: Observable<T> ->
                observable.subscribeOn(Schedulers.computation())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        
        fun <T> applyHandlerStartFinish(
            src: Observable<T>,
            start: Runnable?,
            finish: Runnable?
        ): Observable<T> =
            Observable.using(
                {
                    start?.run()
                    null
                },
                {
                    src
                },
                {
                    finish?.run()
                }
            )
    }
}
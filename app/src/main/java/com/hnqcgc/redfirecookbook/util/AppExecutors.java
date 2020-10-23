package com.hnqcgc.redfirecookbook.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private static Executor mIOExecutor;

    public static Executor getMIOExecutor() {
        if (mIOExecutor == null) {
            mIOExecutor = Executors.newSingleThreadExecutor();
        }
        return mIOExecutor;
    }

}

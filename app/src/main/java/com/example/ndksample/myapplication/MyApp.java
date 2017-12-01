package com.example.ndksample.myapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;

public class MyApp extends Application{

    static public final String TAG = "clojure";

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Class dalvikCLclass = Class.forName("clojure.lang.DalvikDynamicClassLoader");
            Method setContext = dalvikCLclass.getMethod("setContext", Context.class);
            setContext.invoke(null, this);
        } catch (ClassNotFoundException e) {
            Log.i(TAG, "DalvikDynamicClassLoader is not found, probably Skummet is used.");
        } catch (Exception e) {
            Log.e(TAG, "setContext method not found, check if your Clojure dependency is correct.");
        }
    }
}

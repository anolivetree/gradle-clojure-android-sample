package com.example.ndksample.myapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;

import clojure.lang.IFn;
import clojure.lang.RT;

public class MyApp extends Application {

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

        new Thread(new Runnable() {
            @Override
            public void run() {
                loadRepl();
            }
        }).start();
    }

    private void loadRepl() {
        IFn load = (IFn) RT.var("clojure.core", "load");
        //load.invoke("/neko/activity");

        try {
            load.invoke("/neko/tools/repl");
            IFn init = (IFn) RT.var("neko.tools.repl", "init");
            init.invoke();
            Log.i(TAG, "repl loaded");
        } catch (Exception e) {
            Log.i(TAG, "Could not find neko.tools.repl, probably Skummet is used.");
        }
    }
}

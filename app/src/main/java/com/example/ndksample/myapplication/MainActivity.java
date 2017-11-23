package com.example.ndksample.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final NoopHostnameVerifier a = NoopHostnameVerifier.INSTANCE;
        //new SSLConnectionSocketFactory(null);
        try {
            Object obj = Class.forName("core.plain.Demo").newInstance();
            Method method = obj.getClass().getMethod("getLocation", null);
            String s = (String)method.invoke(obj);
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

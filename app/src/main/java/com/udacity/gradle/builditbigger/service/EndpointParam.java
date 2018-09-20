package com.udacity.gradle.builditbigger.service;

import android.content.Context;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.testutil.SimpleIdlingResource;

/**
 * Created by rlima on 19/09/18.
 */

public class EndpointParam {
    private Context mContext;
    private String name;
    private SimpleIdlingResource mSimpleIdlingResource;
    private MainActivity.JokeConsumer listener;

    public EndpointParam(Context context, String name, SimpleIdlingResource simpleIdlingResource, MainActivity.JokeConsumer listener) {
        setContext(context);
        setName(name);
        setSimpleIdlingResource(simpleIdlingResource);
        setListener(listener);
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SimpleIdlingResource getSimpleIdlingResource() {
        return mSimpleIdlingResource;
    }

    public void setSimpleIdlingResource(SimpleIdlingResource mSimpleIdlingResource) {
        this.mSimpleIdlingResource = mSimpleIdlingResource;
    }

    public MainActivity.JokeConsumer getListener() {
        return listener;
    }

    public void setListener(MainActivity.JokeConsumer listener) {
        this.listener = listener;
    }
}

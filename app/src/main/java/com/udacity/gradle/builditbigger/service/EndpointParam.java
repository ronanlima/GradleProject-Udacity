package com.udacity.gradle.builditbigger.service;

import android.content.Context;

import com.udacity.gradle.builditbigger.testutil.SimpleIdlingResource;

/**
 * Created by rlima on 19/09/18.
 */

public class EndpointParam {
    private Context mContext;
    private String name;
    private SimpleIdlingResource mSimpleIdlingResource;

    public EndpointParam(Context context, String name, SimpleIdlingResource simpleIdlingResource) {
        setContext(context);
        setName(name);
        setSimpleIdlingResource(simpleIdlingResource);
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
}

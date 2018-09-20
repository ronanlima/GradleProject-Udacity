package com.udacity.gradle.builditbigger.service;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.testutil.SimpleIdlingResource;
import com.udacity.ronanlima.jokeandroidlib.JokeLibActivity;

import java.io.IOException;

/**
 * Created by rlima on 18/09/18.
 */

public class EndpointAsyncTask extends AsyncTask<EndpointParam, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    @Nullable
    private SimpleIdlingResource mSimpleIdlingResource;
    private MainActivity.JokeConsumer listener;

    @Override
    protected String doInBackground(EndpointParam... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].getContext();
        String name = params[0].getName();
        mSimpleIdlingResource = params[0].getSimpleIdlingResource();
        listener = params[0].getListener();

        if (mSimpleIdlingResource != null) {
            mSimpleIdlingResource.setIdleState(false);
        }

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (mSimpleIdlingResource != null) {
            mSimpleIdlingResource.setIdleState(true);
        }

        listener.run(result);
    }
}
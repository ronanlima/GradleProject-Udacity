package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.service.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.service.EndpointParam;
import com.udacity.gradle.builditbigger.testutil.SimpleIdlingResource;
import com.udacity.ronanlima.jokeandroidlib.JokeLibActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Nullable
    private SimpleIdlingResource mSimpleIdlingResource;
    @BindView(R.id.progressBar1)
    ProgressBar mProgressBar;

    private JokeConsumer listenerJoke = new JokeConsumer() {
        @Override
        public void run(String result) {
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, JokeLibActivity.class);
            i.putExtra(Intent.EXTRA_TEXT, result);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.fragment_container, new MainActivityFragment(), MainActivityFragment.TAG).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointAsyncTask().execute(new EndpointParam(this, "Manfred", mSimpleIdlingResource, listenerJoke));
        MainActivityFragment fragment = (MainActivityFragment) getSupportFragmentManager().findFragmentByTag(MainActivityFragment.TAG);
        if (fragment != null) {
            fragment.showAds();
        }
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mSimpleIdlingResource == null) {
            mSimpleIdlingResource = new SimpleIdlingResource();
        }
        return mSimpleIdlingResource;
    }

    public interface ButtonJokeListener {
        void showAds();
    }

    public interface JokeConsumer {
        void run(String result);
    }
}

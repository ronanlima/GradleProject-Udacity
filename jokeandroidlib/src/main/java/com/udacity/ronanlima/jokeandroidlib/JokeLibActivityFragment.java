package com.udacity.ronanlima.jokeandroidlib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeLibActivityFragment extends Fragment {

    public JokeLibActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_joke_lib, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String piada = getArguments().getString(JokeLibActivity.BUNDLE_JOKE, "");
        TextView tvJoke = (TextView) view.findViewById(R.id.tv_joke_another_project);
        tvJoke.setText(piada);
    }
}

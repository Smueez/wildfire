package com.example.wildfire.ui.fire_fighters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.wildfire.R;

public class FireFightersFragment extends Fragment {

    private FireFightersViewModel fireFightersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fireFightersViewModel =
                ViewModelProviders.of(this).get(FireFightersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fire_fighters, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        fireFightersViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
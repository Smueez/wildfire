package com.example.wildfire.ui.report_fire;

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

public class ReportFireFragment extends Fragment {

    private ReportFireViewModel reportFireViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        reportFireViewModel =
                ViewModelProviders.of(this).get(ReportFireViewModel.class);
        View root = inflater.inflate(R.layout.fragment_report_fire, container, false);
        final TextView textView = root.findViewById(R.id.text_report_fire);
        reportFireViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
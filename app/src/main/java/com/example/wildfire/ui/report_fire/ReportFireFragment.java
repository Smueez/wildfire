package com.example.wildfire.ui.report_fire;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.wildfire.R;

public class ReportFireFragment extends Fragment {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ReportFireViewModel reportFireViewModel;
    private Button openCameraButton;
    private ImageView imageOfCamera;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageOfCamera.setImageBitmap(imageBitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageOfCamera=view.findViewById(R.id.image_of_camera);
        openCameraButton=view.findViewById(R.id.open_camera_button);
        openCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }




}
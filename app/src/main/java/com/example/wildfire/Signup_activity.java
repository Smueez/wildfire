package com.example.wildfire;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_activity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText editText_email, editText_name, editText_pass;
    String email_str, pass_str, name_str, TAG = "sign up page", provider;
    FirebaseUser user;
    Location location;
    LocationManager locationManager;
    float lat, lng;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editText_email = findViewById(R.id.email_signup);
        editText_name = findViewById(R.id.name);
        editText_pass = findViewById(R.id.pass_signup);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Log.d(TAG, "onCreate: "+locationManager);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);
        Log.d(TAG, "onCreate: provider "+provider);


        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        else {
            Log.d(TAG, "onCreate: all fine");
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        else {
            Log.d(TAG, "onCreate: all fine");
        }
        location = locationManager.getLastKnownLocation(provider);
        Log.d(TAG, "onCreate: location "+locationManager.getLastKnownLocation(provider));
        try {
            lat = (float) location.getLatitude();
            lng = (float) location.getLongitude();
        } catch (Exception e){
            Log.d(TAG, "onCreate: "+e.getMessage());
        }
        Log.d(TAG, "onStart: "+lat+" "+lng);

    }

   public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);
            }
        }
    }

   /* @Override
    protected void onStart() {
        super.onStart();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        provider = locationManager.getBestProvider(new Criteria(), false);


        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        location = locationManager.getLastKnownLocation(provider);
        try {
            lat = location.getLatitude();
            lng = location.getLongitude();
        } catch (Exception e){
            Log.d(TAG, "onCreate: "+e.getMessage());
        }
        Log.d(TAG, "onStart: "+lat+" "+lng);
    }*/

    public void signup(View view) {
        try {
            lat = (float) location.getLatitude();
            lng = (float) location.getLongitude();
            Log.d(TAG, "signup: lat lng"+lat+" "+lng);
        } catch (Exception e){
            e.printStackTrace();

        }

        email_str = editText_email.getText().toString().trim();
        pass_str = editText_pass.getText().toString().trim();
        name_str = editText_name.getText().toString().trim();
        if (!email_str.isEmpty() && !pass_str.isEmpty() && !name_str.isEmpty()){
            auth.createUserWithEmailAndPassword(email_str, pass_str)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                user = auth.getCurrentUser();
                                String emailM = email_str.replace(".com","");
                                String emailX = emailM.replace(".","");
                                Users users = new Users(name_str,email_str,pass_str,lat,lng);
                                databaseReference.child("user").child(emailX).setValue(users);
                                //update ato map UI
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }
    }
}

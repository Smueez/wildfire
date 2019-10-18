package com.example.wildfire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_activity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    TextView textView_error;
    EditText editText_email,editText_pass;
    String TAG = "login page", email_str,pass_str;
    Intent intent_home,intent_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        auth = FirebaseAuth.getInstance();
        editText_email = findViewById(R.id.email);
        editText_pass = findViewById(R.id.password);
        textView_error = findViewById(R.id.error);
        intent_signup = new Intent(this,Signup_activity.class);
        intent_home = new Intent(this,HomeActivity.class);
    }
    public void signup(View view){
        startActivity(intent_home);
        finish();
       // startActivity(intent_signup);
    }
    public void signin(View view){
        email_str = editText_email.getText().toString().trim();
        pass_str = editText_pass.getText().toString().trim();
        if (!email_str.isEmpty() && !pass_str.isEmpty()){
            auth.signInWithEmailAndPassword(email_str, pass_str)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                user = auth.getCurrentUser();
                                // update activity
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                textView_error.setText("Email of password is wrong!");
                                textView_error.setVisibility(View.VISIBLE);
                            }

                            // ...
                        }
                    });
        }
    }
}

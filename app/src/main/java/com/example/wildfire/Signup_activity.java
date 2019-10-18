package com.example.wildfire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Signup_activity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText editText_email,editText_name,editText_pass;
    String email_str,pass_str,name_str,TAG = "sign up page";
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        auth = FirebaseAuth.getInstance();
        editText_email = findViewById(R.id.email_signup);
        editText_name = findViewById(R.id.name);
        editText_pass = findViewById(R.id.pass_signup);
    }
    public void signup(View view){
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

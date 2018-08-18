package com.Steven.movieApplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
/**
 * Created by Steven on 6/2/2018.
 * This class is for the user's login and registration based on the firebase
 * use firbase.auth
 */
public class MainActivity extends AppCompatActivity implements
        FirebaseAuth.AuthStateListener,
        View.OnClickListener {
    private Button mRegisterButton;
    private Button mLogInButton;
    private EditText mEmailAddress;
    private EditText mPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        FirebaseAuth.getInstance().signOut();
        mEmailAddress = findViewById(R.id.input_username);
        mPassword = findViewById(R.id.input_password);
        mRegisterButton = findViewById(R.id.link_signup);
        mLogInButton = findViewById(R.id.btn_login);
        mRegisterButton.setOnClickListener(this);
        mLogInButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(this);
    }

    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mRegisterButton)
            registerAccount();
        else if (view == mLogInButton)
            logInAccount();
    }
// registration activity
    private void registerAccount() {

        String email = mEmailAddress.getText().toString();
        String password = mPassword.getText().toString();

        if (isEmailValid(email) && password.length() > 4) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnFailureListener(this,
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "Register failed,user has been registered",
                                            Toast.LENGTH_SHORT).show();
                                    //    createSnackbar(e.getMessage());
                                }
                            });
        } else {
            Toast.makeText(MainActivity.this, "email address should be valid and password length should larger than 4",
                    Toast.LENGTH_SHORT).show();

        }
    }
//login activity
    private void logInAccount() {
        String email = mEmailAddress.getText().toString();
        String password = mPassword.getText().toString();
        if (isEmailValid(email) && password.length() > 4) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnFailureListener(this,
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(MainActivity.this, " username or password is wrong",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
        } else {
            Toast.makeText(MainActivity.this, "email address should be valid and password length should larger than 4",
                    Toast.LENGTH_SHORT).show();

        }
    }
//link to the menu page
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser() != null) {
            String email = mEmailAddress.getText().toString();
            String password = mPassword.getText().toString();
            Intent i = new Intent(this, HomeMainActivity.class);
            startActivity(i);
            finish();

        }
    }
    private boolean isEmailValid(String email) {

        return email.contains("@");
    }
}





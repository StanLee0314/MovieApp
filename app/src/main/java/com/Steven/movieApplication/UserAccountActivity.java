package com.Steven.movieApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.Steven.movieApplication.model.TicketDetail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;

/**
 * Created by steven on 6/4/2018.
 * handle the user account profile.
 * showing the ticket they have bought and user information.
 * use firebase
 */

public class UserAccountActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    TextView tvEmail;
    TextView tvMovie;
    TextView tvTime;
    TextView tvCinema;
    private Button btn_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        TextView tvEmail = (TextView) findViewById(R.id.userEmailText);
        TextView tvMovie = (TextView) findViewById(R.id.bought_movie);
        TextView tvTime = (TextView) findViewById(R.id.bought_time);
        TextView tvCinema = (TextView) findViewById(R.id.bought_cinema);
        Button btn_Back = (Button)findViewById(R.id.backhome);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        String uid = firebaseUser.getUid();
        DatabaseReference myRef = database.getReference("User").child(uid);

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToHome();

            }

        });
// get the ticket data from firebase.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                 String movieName = dataSnapshot.child("ticketid").child("movie_name").getValue().toString();
                 tvMovie.setText(movieName);
                String movieTime = dataSnapshot.child("ticketid").child("time").getValue().toString();
                tvTime.setText(movieTime);
                String cinema = dataSnapshot.child("ticketid").child("cinema").getValue().toString();
                tvCinema.setText(cinema);

            }catch (Exception e){
                    tvMovie.setText("No movie has been booked");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

// get the user information.
        String email = firebaseUser.getEmail();
        tvEmail.setText(email);
    }
    //back to menu
    private void backToHome(){
        Intent i = new Intent(this,HomeMainActivity.class);
        startActivity(i);
    }
}
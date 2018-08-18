package com.Steven.movieApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by steven on 6/3/2018.
 * handle the purchase process and upload the data to the firebase.
 * use HashMap ,firebase
 */

public class PurchaseProcess extends AppCompatActivity {
    private Spinner spCinema;
    private Spinner spTime;
    private Spinner spAmount;
    private Button btnBook;
    private RadioGroup rgAdditional;
    private RadioButton rbAdditional;
    private FirebaseAuth mAuth;
    private String movieName;
    FirebaseUser firebaseUser;


    private List<String> cinema, timeList,amountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_detail);
        spCinema = (Spinner) findViewById(R.id.movie_cinema);
        spTime = (Spinner) findViewById(R.id.movie_time);
        spAmount = (Spinner) findViewById(R.id.movie_amount);
        btnBook = (Button) findViewById(R.id.btn_book);
        rgAdditional = (RadioGroup) findViewById(R.id.rg_additional);
        mAuth = FirebaseAuth.getInstance();
        // handle the purchase options
        firebaseUser = mAuth.getCurrentUser();
        cinema = new ArrayList<String>();
        cinema.add("Chadstone Cinema");
        cinema.add("CBD Cinema");
        cinema.add("Caulfield Cinema");

        timeList = new ArrayList<String>();
        timeList.add("10am");
        timeList.add("12am");
        timeList.add("4pm");
        timeList.add("8pm");

        amountList = new ArrayList<String>();
        amountList.add("1");
        amountList.add("2");
        amountList.add("3");
        amountList.add("4");
        amountList.add("5");
        amountList.add("6");

       movieName =  getIntent().getStringExtra("title");
        ArrayAdapter<String> cinemaAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, cinema);
        spCinema.setAdapter(cinemaAdapter);
        ArrayAdapter<String> cinemaAdapter1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, amountList);
        spAmount.setAdapter(cinemaAdapter1);
        ArrayAdapter<String> cinemaAdapter2 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, timeList);
        spTime.setAdapter(cinemaAdapter2);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int selectedId = rgAdditional.getCheckedRadioButtonId();
                    rbAdditional = (RadioButton) findViewById(selectedId);
                    writeToDB();

                }

        });
    }
    //upload data to firebase.
    private void writeToDB(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Ref = database.getReference("User");
        String uid = firebaseUser.getUid();
        Map map = new HashMap<>();
        map.put("movie_name", movieName);
        map.put("cinema", spCinema.getSelectedItem().toString());
        map.put("time", spTime.getSelectedItem().toString());
        map.put("amount", spAmount.getSelectedItem().toString());
        map.put("Snakes",rbAdditional.getText().toString() );
        Ref.child(uid).child("ticketid").setValue(map);
        Toast.makeText(this, " Purchase Successful",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, HomeMainActivity.class);
        startActivity(i);
    }
}




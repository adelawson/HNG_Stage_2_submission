package com.example.hng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Get button and set on click listener to display toast message when user provides an input
        Button displayButton = findViewById(R.id.display_button);
        EditText nameField = findViewById(R.id.name_field);
        displayButton.setOnClickListener(v -> {

//if statements to ensure that when user doesn't provide input a message pops up to ask for input
            if ((nameField.getText().length()) == 0) {
                Toast noText = Toast.makeText(MainActivity.this,
                        ("Please type a name to be displayed"),Toast.LENGTH_SHORT);
                noText.setGravity(Gravity.CENTER_VERTICAL,0,0);
                noText.show();
            } else if ((nameField.getText().length()) != 0) {
                String fullName = nameField.getText().toString();
                Toast textToast= Toast.makeText(MainActivity.this,
                        ("Your full name is " + fullName), Toast.LENGTH_LONG);
                textToast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                textToast.show();
            }
        });
//set onClick listener for imageviews to open websites of the respective brands using imageUrl
// function
        ImageView zuriImage = findViewById(R.id.zuri_img);
        imageUrl(zuriImage,"https://internship.zuri.team/");

        ImageView hngImage = findViewById(R.id.hng_logo);
        imageUrl(hngImage,"https://hng.tech/");

        ImageView i4gImage = findViewById(R.id.i4g_logo);
        imageUrl(i4gImage,"https://ingressive.org/");


    }
// function to take in image view and set onclick listener to open a browser url on click
    private void imageUrl (ImageView imgvw, String url){
        Uri uriUrl = Uri.parse(url);
        imgvw.setOnClickListener(v -> {
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);

        });

    }

}
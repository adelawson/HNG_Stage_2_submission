package com.example.hng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
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
                dismissKeyBoard();
                popUpCreator(v,fullName);
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

//dismiss keyboard
    private void dismissKeyBoard() {
        InputMethodManager keyboardManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if(keyboardManager.isAcceptingText()) {
            keyboardManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void popUpCreator(View view,String msg) {

//inflate popup window layout setting it to popup window xml
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window,null);
        PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);


        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

//code to change text in popup window
        TextView popText= popupWindow.getContentView().findViewById(R.id.pop_up_text);
        popText.setText(msg);

// Dim background on popup
        View container = (View) popupWindow.getContentView().getParent();
        Context context = popupWindow.getContentView().getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.3f;
        wm.updateViewLayout(container, p);

//Dismiss popup window when you touch outside the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}
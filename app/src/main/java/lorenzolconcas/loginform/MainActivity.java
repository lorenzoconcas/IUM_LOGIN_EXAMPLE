package lorenzolconcas.loginform;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //gestione dell'aspetto

        setContentView(R.layout.main);
       
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        getWindow().setStatusBarColor(Color.TRANSPARENT);
        try {
            getSupportActionBar().hide();
        }catch(Exception e){
            e.printStackTrace();
        }




        //assegno una funzione al tasto login
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });



    }
    private void Login(){


        EditText usernameField = findViewById(R.id.usernameField);
        EditText passwordField = findViewById(R.id.passwordField);



        if(isEmpty(usernameField) || isEmpty(passwordField)){
            Toast toast = Toast.makeText(getApplicationContext(), "Username o password vuoti", Toast.LENGTH_SHORT);
            toast.show();
            ReactIfEmpty(usernameField);
            ReactIfEmpty(passwordField);
        }else{
            Intent myIntent = new Intent(this, LoggedResult.class);
            myIntent.putExtra("username", usernameField.getText().toString()); //Optional parameters
            myIntent.putExtra("password", passwordField.getText().toString()); //Optional parameters
            startActivity(myIntent);
        }

    }
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }


    public void ReactIfEmpty(final EditText editText){
        if(isEmpty(editText)) {
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(Color.WHITE);
            gd.setCornerRadius(5);
            gd.setStroke(5, Color.RED);
            editText.setBackground(gd);


            final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);

            editText.startAnimation(animShake);

            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if(hasFocus){
                        GradientDrawable gd = new GradientDrawable();
                        gd.setColor(Color.WHITE);
                        gd.setCornerRadius(5);
                        gd.setStroke(5, Color.WHITE);
                        editText.setBackground(gd);
                    }
                }
            });

        }
    }
}

package lorenzolconcas.loginform;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import lorenzolconcas.loginform.Utils.Behavior;
import lorenzolconcas.loginform.data.Database;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init del db
        Database db = new Database();


        //gestione dell'aspetto

        setContentView(R.layout.main);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        //colore della statusbar
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        try {

                ActionBar ab = getSupportActionBar();
                ab.hide();
        }catch(NullPointerException e){
            e.printStackTrace();
        }

        //setta un click listener alla view che mostra il tasto "registra"
        //non era da fare ma l'ho fatto per me
        /*TextView register = findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), Registration.class);
                startActivity(myIntent);
            }
        });*/


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

        //Controllo che i due campi di testo non siano vuoti e procedo a chiamare una seconda activity
        EditText usernameField = findViewById(R.id.usernameField);
        EditText passwordField = findViewById(R.id.passwordField);

        Behavior b = new Behavior();

        if(b.isEmpty(usernameField) || b.isEmpty(passwordField)){
            Toast toast = Toast.makeText(getApplicationContext(), "Username o password vuoti", Toast.LENGTH_SHORT);
            toast.show();
            b.ReactIfEmpty(this,usernameField);
            b.ReactIfEmpty(this, passwordField);
        }else{
            Intent myIntent = new Intent(this, LoggedResult.class);
            myIntent.putExtra("username", usernameField.getText().toString()); //Optional parameters
            myIntent.putExtra("password", passwordField.getText().toString()); //Optional parameters
            startActivity(myIntent);
        }

    }

}

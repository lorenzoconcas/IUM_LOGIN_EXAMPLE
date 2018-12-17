package lorenzolconcas.loginform;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lorenzolconcas.loginform.data.Database;

public class LoggedResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_logged_result);
        Database db = Database.getPersist();

        super.onCreate(savedInstanceState);

        //imposto alcune cose dell'aspetto
        setContentView(R.layout.activity_logged_result);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getSupportActionBar().hide();


        //comportamento tasto indietro
        Button b = findViewById(R.id.close_activity);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView result = findViewById(R.id.action_result);
        //verifico il login...
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        if(db.Login(username, password))
            result.setText(getString(R.string.greeting)+" "+username);
        else
            result.setText(R.string.access_denied);
    }



}

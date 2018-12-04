package lorenzolconcas.loginform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lorenzolconcas.loginform.Utils.Behavior;
import lorenzolconcas.loginform.data.Database;

public class Registration extends AppCompatActivity {

    Database db = Database.getPersist();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        try{
            getSupportActionBar().hide();
        }catch (NullPointerException e){
            e.printStackTrace();
        }




        Button registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registra();
            }
        });
    }
    private void Registra(){

        boolean result = false;
        Toast t = new Toast(this);

        EditText usernameField = findViewById(R.id.usernameField);
        EditText passwordField = findViewById(R.id.passwordField);
        EditText confirmPasswordField = findViewById(R.id.confirm_password);

        Behavior b = new Behavior();

        b.ReactIfEmpty(this, usernameField);
        b.ReactIfEmpty(this, passwordField);
        b.ReactIfEmpty(this, confirmPasswordField);


        if(b.isEmpty(usernameField) || b.isEmpty(passwordField) || b.isEmpty(confirmPasswordField)){
            t.makeText(this, R.string.insert_all_information, Toast.LENGTH_SHORT).show();
        }else {
            if (passwordField.getText().toString().equals(confirmPasswordField.getText().toString())) {
                result = db.insertUser(usernameField.getText().toString(), passwordField.getText().toString());
                if (result) {
                    t.makeText(this, R.string.registered, Toast.LENGTH_SHORT).show();
                } else {
                    t.makeText(this, R.string.username_already_taken, Toast.LENGTH_SHORT).show();
                }

            } else {

                b.React(this, confirmPasswordField);

                t.makeText(this, getString(R.string.password_not_match), Toast.LENGTH_SHORT).show();
            }

        }
        if(result) finish();
    }
}

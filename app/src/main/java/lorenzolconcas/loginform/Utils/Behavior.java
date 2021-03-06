package lorenzolconcas.loginform.Utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import lorenzolconcas.loginform.R;

public class Behavior {

    //Controlla se un campo di testo è vuoto
    public  boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    //se il campo di testo è vuoto mostra un animazione e un errore
    public void ReactIfEmpty(Context context,final EditText editText){
        if(isEmpty(editText)) {
           React(context, editText);

        }
    }
    //animazione dell'editext vuoto
    public void React(Context context, final EditText editText){
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.WHITE);
        gd.setCornerRadius(5);
        gd.setStroke(5, Color.RED);
        editText.setBackground(gd);
        editText.setError("Campo necessario");

        final Animation animShake = AnimationUtils.loadAnimation(context, R.anim.shake);

        editText.startAnimation(animShake);

        
        //ripristina l'aspetto della view se essa viene premuta
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

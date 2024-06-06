package in.Project;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Common {

        Common(Context cont, String to_be_print){
            Toast.makeText(cont, to_be_print, Toast.LENGTH_LONG).show();
        }

        Common(View vw, String message){
            Snackbar.make(vw, message, Snackbar.LENGTH_LONG).show();
        }

        /*Common(Context cont, Class<?> any){
            Intent intent = new Intent(cont, any);
            startActivity(intent);
        }*/
}

package in.Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    TextView hp_signup, forgotPassword;
    ImageView eye_icon_hidden, eye_icon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.main_username);
        password = findViewById(R.id.main_password);
        login = findViewById(R.id.hp_login);
        hp_signup = findViewById(R.id.hp_signUp);
        forgotPassword = findViewById(R.id.forgot_password);

        forgotPassword.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        eye_icon = findViewById(R.id.pwd_eye_icon);
        eye_icon_hidden = findViewById(R.id.pwd_eye_icon_hidden);

        eye_icon_hidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eye_icon_hidden.setVisibility(v.GONE);
                eye_icon.setVisibility(v.VISIBLE);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        eye_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eye_icon.setVisibility(View.GONE);
                eye_icon_hidden.setVisibility(View.VISIBLE);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                if(username.getText().toString().equals("")){
                    username.setError("Invalid input");
                }

                else  if (password.getText().toString().equals("")){
                    password.setError("Invalid input");
                }

                else{
                    Log.d("RESPONSE","Login Successful");
                    Toast.makeText(MainActivity.this,"Login Succesfully", Toast.LENGTH_LONG).show();
                    Snackbar.make(vw,"Login successfull", Snackbar.LENGTH_LONG).show();
                    Intent dashboard_intent = new Intent(MainActivity.this, DashboardActivity
                            .class);
                    startActivity(dashboard_intent);
                }
            }
        });

        hp_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent sg_intent = new Intent(MainActivity.this, SignupActivity.class);
             startActivity(sg_intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fp_intent = new Intent (MainActivity.this,
                        ForgotPasswordActivity.class);
                startActivity(fp_intent);
            }
        });
    }
}
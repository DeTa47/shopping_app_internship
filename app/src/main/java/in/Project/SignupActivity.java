package in.Project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignupActivity extends AppCompatActivity {

    EditText name, email, mobile, username, pwd;

    RadioGroup gender;
    ImageView pwd_hidden_icon, pwd_visible_icon;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.signup_name);
        email = findViewById(R.id.signup_email);
        mobile = findViewById(R.id.signup_mobileno);
        username = findViewById(R.id.signup_username);
        pwd = findViewById(R.id.signup_password);
        pwd_hidden_icon = findViewById(R.id.pwd_eye_icon_hidden);
        pwd_visible_icon = findViewById(R.id.pwd_eye_icon);
        signup = findViewById(R.id.signup);
        gender = findViewById(R.id.signup_gender);

        gender.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton button = findViewById(checkedId);

        });

    }
}
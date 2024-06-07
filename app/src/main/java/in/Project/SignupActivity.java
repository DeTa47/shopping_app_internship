package in.Project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class SignupActivity extends AppCompatActivity {

    EditText name, email, mobile, username, pwd, confirm_pwd;

    RadioGroup gender;
    ImageView pwd_hidden_icon, pwd_visible_icon, pwd_cph_icon, pwd_cpv_icon;
    Button signup;

    CheckBox TandC;
    Spinner city;
    String cities [] = {"Select city","Vadodara", "Ahmedabad", "Rajkot", "Surat"};
    String sCity;

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
        pwd_cph_icon = findViewById(R.id.pwd_eye_icon_hidden_cp);
        pwd_cpv_icon = findViewById(R.id.pwd_eye_icon_cp);
        confirm_pwd = findViewById(R.id.signup_confirm_password);
        signup = findViewById(R.id.signup);
        gender = findViewById(R.id.signup_gender);
        TandC = findViewById(R.id.TandC);

        city = findViewById(R.id.signup_city);
        ArrayAdapter adapter = new ArrayAdapter(SignupActivity.this, android.R.layout.simple_list_item_checked, cities);
        city.setAdapter(adapter);

        city.getOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){


                    if(i == 0){
                        sCity="";

                    }
                    else {
                        sCity = cities[i];
                        new Common(SignupActivity.this, cities[i]);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });

        pwd_visible_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd_visible_icon.setVisibility(View.GONE);
                pwd_hidden_icon.setVisibility(View.VISIBLE);
                pwd.setTransformationMethod(new PasswordTransformationMethod());
            }

        });

        pwd_hidden_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd_visible_icon.setVisibility(View.VISIBLE);
                pwd_hidden_icon.setVisibility(View.GONE);
                pwd.setTransformationMethod(new HideReturnsTransformationMethod());
            }
        });
        pwd_cph_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd_cpv_icon.setVisibility(View.GONE);
                pwd_cph_icon.setVisibility(View.VISIBLE);
                pwd.setTransformationMethod(new PasswordTransformationMethod());
            }
        });
        pwd_cpv_icon.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               pwd_cpv_icon.setVisibility(View.GONE);
               pwd_cph_icon.setVisibility(View.VISIBLE);
               pwd.setTransformationMethod(new PasswordTransformationMethod());
           }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().equals("")){
                    username.setError("Username Required");
                }
                else if(name.getText().toString().trim().equals("")){
                    name.setError("Name Required");
                }

                else if(mobile.getText().toString().trim().equals("")){
                    mobile.setError("Mobile number Required");
                }
                else if(email.getText().toString().trim().equals("")){
                    email.setError("Email Required");
                }
                else if(pwd.getText().toString().trim().equals("")){
                    pwd.setError("Password Required");
                }
                else if (confirm_pwd.getText().toString().trim().equals("")){
                    confirm_pwd.setError("Type password again");
                }
                else if (pwd.getText().toString().trim().length() < 6){
                    pwd.setError("Minimum 6 characters required");
                }
                else if (!confirm_pwd.getText().toString().trim().matches(pwd.getText().toString().trim())){
                    confirm_pwd.setError("Passwords don't match");
                }
                else if (!TandC.isChecked()){
                    new Common(v, "Please check the Terms and Conditions");

                }
                else if (gender.getCheckedRadioButtonId() == -1){
                    new Common(v, "Gender required");
                }
                else if (sCity.equals("")){
                    new Common(v, "Please select your city");
                }
                else{
                    new Common(v, "Signup Successful");
                    onBackPressed();
                }
            }
        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = findViewById(checkedId);
                //new Common(SignupActivity.this, button.getText().toString());
            }
        });

    }
}
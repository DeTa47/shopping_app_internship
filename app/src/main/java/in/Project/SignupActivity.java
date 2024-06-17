package in.Project;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    String sGender;
    CheckBox TandC;
    Spinner city;
    String cities [] = {"Select city","Vadodara", "Ahmedabad", "Rajkot", "Surat"};
    String sCity;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = openOrCreateDatabase("InternshipMay24.db", MODE_PRIVATE, null);
        String create_db = "CREATE TABLE IF NOT EXISTS USERS(USERID INTEGER PRIMARY KEY, USERNAME VARCHAR(100), NAME VARCHAR(100), " +
                "EMAIL VARCHAR(100), CONTACT INTEGER(10), PASSWORD VARCHAR(100), CITY VARCHAR(30), GENDER VARCHAR(6))" ;
        db.execSQL(create_db);

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

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
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

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = findViewById(checkedId);
                sGender = button.getText().toString();
                //new Common(SignupActivity.this, button.getText().toString());
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

                    String selectQuery = "SELECT * FROM USERS WHERE USERNAME='"+username.getText().toString()+"' OR EMAIL='"+email.getText().toString()+"' OR CONTACT = '"+mobile.getText().toString()+"'";
                    Cursor cursor = db.rawQuery(selectQuery, null);
                    if(cursor.getCount()>0){
                        new Common(v, "Person already exists");
                    }


                    String insertQuery = "INSERT INTO USERS(USERNAME, NAME, EMAIL, CONTACT, PASSWORD, CITY, GENDER) VALUES('"+username.getText().toString()+"','"+name.getText().toString()+"','"+email.getText().toString()+"','"+mobile.getText().toString()+"','"+pwd.getText().toString()+"','"+sCity+"','"+sGender+"')";
                    db.execSQL(insertQuery);
                    new Common(v, "Signup Successful");
                    onBackPressed();
                }
            }
        });


    }
}
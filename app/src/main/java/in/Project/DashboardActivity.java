package in.Project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    SharedPreferences sp;
    TextView dashboard_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dashboard_text = findViewById(R.id.dashboard_text);

        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);

        dashboard_text.setText("Welcome "+sp.getString(ConstantSp.Name, ""));

    }
}
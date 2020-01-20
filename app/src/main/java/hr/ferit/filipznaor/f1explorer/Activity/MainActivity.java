package hr.ferit.filipznaor.f1explorer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hr.ferit.filipznaor.f1explorer.R;

public class MainActivity extends AppCompatActivity {

    private Button mBtnSeasons, mBtnDrivers, mBtnConstructors, mBtnCircuits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpLayout();
    }

    private void setUpLayout(){
        mBtnSeasons = findViewById(R.id.btnSeasons);
        mBtnDrivers = findViewById(R.id.btnDrivers);
        mBtnConstructors = findViewById(R.id.btnConstructors);
        mBtnCircuits = findViewById(R.id.btnCircuits);
        setUpListeners();
    }

    private void setUpListeners(){
        mBtnSeasons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SeasonIndexActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        mBtnDrivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DriverIndexActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        mBtnConstructors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ConstructorIndexActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        mBtnCircuits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, CircuitIndexActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}

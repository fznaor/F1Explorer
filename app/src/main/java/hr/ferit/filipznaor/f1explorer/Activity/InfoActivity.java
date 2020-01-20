package hr.ferit.filipznaor.f1explorer.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hr.ferit.filipznaor.f1explorer.API.CountryParser;
import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.POJO.Season;
import hr.ferit.filipznaor.f1explorer.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class InfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    protected String mId, mName, mCountry;
    protected RecyclerView mRecyclerView;
    protected Spinner mSpinner;
    protected TextView mTvStandings, mTvName;
    protected Call<APIResponse> mApiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        mName = intent.getStringExtra("name");
        mCountry = intent.getStringExtra("country");
        mTvStandings = findViewById(R.id.tvInfoStandings);
        mTvName = findViewById(R.id.tvInfoName);
        setTvNameText();
        setUpToolbar();
        setUpSpinner();
        setUpRecycler();
        setUpApiCall();
    }

    public abstract void setTvNameText();

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbarInfo);
        toolbar.setTitle(mName);
        setSupportActionBar(toolbar);
        ImageView ivCountry = findViewById(R.id.ivInfoCountry);
        Picasso.get().load("https://www.countryflags.io/" + CountryParser.getCountryCode(mCountry) + "/flat/64.png").resize(80,80).into(ivCountry);
    }

    private void setUpSpinner(){
        mSpinner = findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(this);
    }

    public abstract void setUpRecycler();

    public abstract Call<APIResponse> setApiCall();

    private void setUpApiCall(){
        mApiCall = setApiCall();
        mApiCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    List<String> seasons = new ArrayList<>();
                    for(Season s : response.body().getSeasons()){
                        seasons.add(String.valueOf(s.getSeason()));
                    }
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(InfoActivity.this, R.layout.spinner_item, seasons);
                    spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    mSpinner.setAdapter(spinnerArrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(InfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mApiCall != null)
            mApiCall.cancel();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String season = mSpinner.getSelectedItem().toString();
        setUpStandingsInfo(season);
        setUpSeasonResults(season);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public abstract void setUpStandingsInfo(String season);

    public abstract void setUpSeasonResults(String season);
}

package hr.ferit.filipznaor.f1explorer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hr.ferit.filipznaor.f1explorer.API.CountryParser;
import hr.ferit.filipznaor.f1explorer.API.NetworkUtils;
import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.RecyclerAdapter.CircuitInfoRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CircuitInfoActivity extends AppCompatActivity {

    private String mCircuitId, mCircuitName, mCountry;
    private CircuitInfoRecyclerAdapter mAdapter;
    private Call<APIResponse> mApiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circuit_info);
        Intent intent = getIntent();
        mCircuitId = intent.getStringExtra("id");
        mCircuitName = intent.getStringExtra("name");
        mCountry = intent.getStringExtra("country");
        setUpToolbar();
        setUpRecycler();
        setUpApiCall();
    }

    private void setUpToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbarCircuitInfo);
        toolbar.setTitle(mCircuitName);
        setSupportActionBar(toolbar);
        ImageView ivCountry = findViewById(R.id.ivCircuitInfoCountry);
        Picasso.get().load("https://www.countryflags.io/" + CountryParser.getCountryCode(mCountry) + "/flat/64.png").resize(80,80).into(ivCountry);
    }

    private void setUpRecycler(){
        RecyclerView rvCircuitInfo = findViewById(R.id.rvCircuitInfo);
        rvCircuitInfo.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CircuitInfoRecyclerAdapter();
        rvCircuitInfo.setAdapter(mAdapter);
    }

    private void setUpApiCall(){
        mApiCall = NetworkUtils.getApiInterface().getCircuitInfo(mCircuitId, 85);
        mApiCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    mAdapter.addSeasons(response.body());
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(CircuitInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mApiCall != null)
            mApiCall.cancel();
    }
}

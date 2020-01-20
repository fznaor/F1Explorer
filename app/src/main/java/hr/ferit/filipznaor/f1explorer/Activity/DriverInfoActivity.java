package hr.ferit.filipznaor.f1explorer.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.widget.Toast;

import hr.ferit.filipznaor.f1explorer.API.NetworkUtils;
import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.RecyclerAdapter.DriverInfoRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverInfoActivity extends InfoActivity {

    private DriverInfoRecyclerAdapter mAdapter;

    public void setTvNameText(){
        mTvName.setText(R.string.stringConstructorLabel);
    }

    public void setUpRecycler(){
        mRecyclerView = findViewById(R.id.rvInfo);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DriverInfoRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    public Call<APIResponse> setApiCall(){
        return NetworkUtils.getApiInterface().getDriverSeasons(mId);
    }

    public void setUpStandingsInfo(String season){
        Call<APIResponse> apiCall = NetworkUtils.getApiInterface().getSingleDriverStandings(season, mId);
        apiCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    if(!response.body().getStandings().isEmpty()) {
                        int position = response.body().getStandings().get(0).getDriverStandings().get(0).getPosition();
                        mTvStandings.setText(getApplicationContext().getString(R.string.stringInfoStandingLabel, String.valueOf(position)));
                        mTvStandings.setVisibility(View.VISIBLE);
                    }
                    else{
                        mTvStandings.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(DriverInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setUpSeasonResults(String season){
        Call<APIResponse> apiCall = NetworkUtils.getApiInterface().getDriverSeasonResults(season, mId);
        apiCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    mAdapter.addData(response.body());
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(DriverInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

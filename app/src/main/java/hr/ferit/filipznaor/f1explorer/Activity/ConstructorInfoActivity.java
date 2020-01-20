package hr.ferit.filipznaor.f1explorer.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.View;
import android.widget.Toast;

import hr.ferit.filipznaor.f1explorer.API.NetworkUtils;
import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.RecyclerAdapter.ConstructorInfoRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConstructorInfoActivity extends InfoActivity {

    private ConstructorInfoRecyclerAdapter mAdapter;

    public void setTvNameText(){
        mTvName.setText(R.string.stringDriversLabel);
    }

    public void setUpRecycler(){
        mRecyclerView = findViewById(R.id.rvInfo);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ConstructorInfoRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    public Call<APIResponse> setApiCall(){
        return NetworkUtils.getApiInterface().getConstructorSeasons(mId,100);
    }

    public void setUpStandingsInfo(final String season){
        Call<APIResponse> apiCall = NetworkUtils.getApiInterface().getSingleConstructorStandings(season, mId);
        apiCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    if(!response.body().getStandings().isEmpty()) {
                        int position = response.body().getStandings().get(0).getConstructorStandings().get(0).getPosition();
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
                Toast.makeText(ConstructorInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setUpSeasonResults(String season){
        Call<APIResponse> apiCall = NetworkUtils.getApiInterface().getConstructorSeasonResults(season, mId, 300);
        apiCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    mAdapter.addRaces(response.body());
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(ConstructorInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

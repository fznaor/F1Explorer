package hr.ferit.filipznaor.f1explorer.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import hr.ferit.filipznaor.f1explorer.API.NetworkUtils;
import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.RecyclerAdapter.SeasonIndexRecyclerAdapter;
import retrofit2.Response;

public class SeasonIndexActivity extends IndexActivity {

    private SeasonIndexRecyclerAdapter mAdapter;

    public void setUpSearch(){
        mEtSearch = findViewById(R.id.etSearch);
        mEtSearch.setVisibility(View.GONE);
    }

    public void filterData(String term){
        mAdapter.filterData(term);
    }

    public void setApiCall(){
        mApiCall = NetworkUtils.getApiInterface().getSeasons(80);
    }

    public void setUpRecycler(){
        RecyclerView recyclerView = findViewById(R.id.rvIndex);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SeasonIndexRecyclerAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    public void addData(Response<APIResponse> response) {
        mAdapter.addData(response.body().getSeasons());
    }
}

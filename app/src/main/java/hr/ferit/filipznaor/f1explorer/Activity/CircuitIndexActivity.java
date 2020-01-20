package hr.ferit.filipznaor.f1explorer.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hr.ferit.filipznaor.f1explorer.API.NetworkUtils;
import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.RecyclerAdapter.CircuitIndexRecyclerAdapter;
import retrofit2.Response;

public class CircuitIndexActivity extends IndexActivity {

    private CircuitIndexRecyclerAdapter mAdapter;

    public void filterData(String term){
        mAdapter.filterData(term);
    }

    public void setApiCall(){
        mApiCall = NetworkUtils.getApiInterface().getCircuits(100);
    }

    public void setUpRecycler(){
        RecyclerView recyclerView = findViewById(R.id.rvIndex);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CircuitIndexRecyclerAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    public void addData(Response<APIResponse> response) {
        mAdapter.addData(response.body().getCircuits());
    }
}

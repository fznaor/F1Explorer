package hr.ferit.filipznaor.f1explorer.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hr.ferit.filipznaor.f1explorer.API.NetworkUtils;
import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.RecyclerAdapter.SeasonInfoRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeasonInfoFragment extends Fragment {

    private SeasonInfoRecyclerAdapter mAdapter;
    private Call<APIResponse> mApiCall;
    private final static String BUNDLE_SEASON = "season";

    public static SeasonInfoFragment newInstance(String season){
        SeasonInfoFragment fragment = new SeasonInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_SEASON, season);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_season_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecycler(view);
        setUpApiCall();
    }

    private void setUpRecycler(View view){
        RecyclerView rvSeasonInfo = view.findViewById(R.id.rvSeasonInfo);
        rvSeasonInfo.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new SeasonInfoRecyclerAdapter();
        rvSeasonInfo.setAdapter(mAdapter);
    }

    private void setUpApiCall(){
        mApiCall = NetworkUtils.getApiInterface().getSeasonInfo(getArguments().getString(BUNDLE_SEASON));
        mApiCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    mAdapter.addRaces(response.body());
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                if(getContext() != null) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mApiCall != null)
            mApiCall.cancel();
    }
}

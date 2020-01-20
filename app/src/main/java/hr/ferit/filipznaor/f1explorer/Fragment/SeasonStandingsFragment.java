package hr.ferit.filipznaor.f1explorer.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hr.ferit.filipznaor.f1explorer.API.NetworkUtils;
import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.R;
import hr.ferit.filipznaor.f1explorer.RecyclerAdapter.SeasonStandingsRecyclerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeasonStandingsFragment extends Fragment {

    private SeasonStandingsRecyclerAdapter mAdapter;
    private Call<APIResponse> mApiCall;
    private TextView mTvErrorMessage;
    private final static String BUNDLE_SEASON = "season";
    private final static String BUNDLE_TYPE = "type";


    public static SeasonStandingsFragment newInstance(String season, StandingsType type){
        SeasonStandingsFragment fragment = new SeasonStandingsFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_SEASON, season);
        args.putSerializable(BUNDLE_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_season_standings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpLayout(view);
        setUpRecycler(view);
        setUpApiCall();
    }

    private void setUpLayout(View view){
        TextView tvStandingsNameLabel = view.findViewById(R.id.tvStandingsNameLabel);
        TextView tvStandingsConstructorLabel = view.findViewById(R.id.tvStandingsConstructorLabel);
        mTvErrorMessage = view.findViewById(R.id.tvStandingsError);
        if(getArguments().getSerializable(BUNDLE_TYPE) == StandingsType.DRIVERS){
            tvStandingsNameLabel.setText(R.string.stringDriverLabel);
            tvStandingsConstructorLabel.setText(R.string.stringConstructorLabel);
        }
        else{
            tvStandingsNameLabel.setText(R.string.stringConstructorLabel);
            tvStandingsConstructorLabel.setVisibility(View.GONE);
        }
    }

    private void setUpRecycler(View view){
        RecyclerView mRvSeasonStandings = view.findViewById(R.id.rvSeasonStandings);
        mRvSeasonStandings.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mAdapter = new SeasonStandingsRecyclerAdapter(getArguments().getSerializable(BUNDLE_TYPE));
        mRvSeasonStandings.setAdapter(mAdapter);
    }

    private void setUpApiCall(){
        if(getArguments().getSerializable(BUNDLE_TYPE) == StandingsType.DRIVERS) {
            mApiCall = NetworkUtils.getApiInterface().getDriverStandings(getArguments().getString(BUNDLE_SEASON), 200);
        }
        else{
            mApiCall = NetworkUtils.getApiInterface().getConstructorStandings(getArguments().getString(BUNDLE_SEASON), 50);
        }
        mApiCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    mAdapter.addStandings(response.body());
                    if(mAdapter.getItemCount() == 0){
                        mTvErrorMessage.setText(R.string.stringStandingsError);
                        mTvErrorMessage.setVisibility(View.VISIBLE);
                    }
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

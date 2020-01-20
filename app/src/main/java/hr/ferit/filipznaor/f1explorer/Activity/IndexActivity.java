package hr.ferit.filipznaor.f1explorer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import hr.ferit.filipznaor.f1explorer.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class IndexActivity extends AppCompatActivity {

    protected EditText mEtSearch;
    protected Call<APIResponse> mApiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        setUpSearch();
        setUpRecycler();
        setUpApiCall();
    }

    public void setUpSearch(){
        mEtSearch = findViewById(R.id.etSearch);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(mEtSearch.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public abstract void filterData(String term);

    public abstract void setUpRecycler();

    public abstract void setApiCall();

    private void setUpApiCall(){
        setApiCall();
        mApiCall.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful()){
                    addData(response);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(IndexActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public abstract void addData(Response<APIResponse> response);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mApiCall != null)
            mApiCall.cancel();
    }
}
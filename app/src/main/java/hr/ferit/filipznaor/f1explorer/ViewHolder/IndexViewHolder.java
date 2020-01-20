package hr.ferit.filipznaor.f1explorer.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import hr.ferit.filipznaor.f1explorer.API.CountryParser;
import hr.ferit.filipznaor.f1explorer.R;

public abstract class IndexViewHolder extends RecyclerView.ViewHolder {

    protected TextView mTvName;
    protected ImageView mIvCountry;
    protected String mId, mCountry;

    public IndexViewHolder(@NonNull View itemView) {
        super(itemView);
        mTvName = itemView.findViewById(R.id.tvName);
        mIvCountry = itemView.findViewById(R.id.ivCountry);
    }

    public void setName(String name){
        mTvName.setText(name);
    }

    public void setCountry(String country){
        String link = "https://www.countryflags.io/" + CountryParser.getCountryCode(country) + "/flat/64.png";
        Picasso.get()
                .load(link)
                .resize(60,70)
                .into(mIvCountry);
        mCountry = country;
    }

    public void setId(String ID){
        mId = ID;
    }
}

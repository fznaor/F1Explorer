package hr.ferit.filipznaor.f1explorer.ViewHolder;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import hr.ferit.filipznaor.f1explorer.Activity.DriverInfoActivity;

public class DriverIndexViewHolder extends IndexViewHolder {

    public DriverIndexViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DriverInfoActivity.class);
                myIntent.putExtra("id", mId);
                myIntent.putExtra("name", mTvName.getText());
                myIntent.putExtra("country", mCountry);
                v.getContext().startActivity(myIntent);
            }
        });
    }
}

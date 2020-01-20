package hr.ferit.filipznaor.f1explorer.ViewHolder;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import hr.ferit.filipznaor.f1explorer.Activity.ConstructorInfoActivity;

public class ConstructorIndexViewHolder extends IndexViewHolder {

    public ConstructorIndexViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), ConstructorInfoActivity.class);
                myIntent.putExtra("id", mId);
                myIntent.putExtra("name", mTvName.getText());
                myIntent.putExtra("country", mCountry);
                v.getContext().startActivity(myIntent);
            }
        });
    }
}

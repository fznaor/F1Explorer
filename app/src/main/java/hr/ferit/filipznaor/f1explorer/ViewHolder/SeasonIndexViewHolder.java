package hr.ferit.filipznaor.f1explorer.ViewHolder;

import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import hr.ferit.filipznaor.f1explorer.Activity.SeasonSummaryActivity;

public class SeasonIndexViewHolder extends IndexViewHolder {

    public SeasonIndexViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), SeasonSummaryActivity.class);
                myIntent.putExtra("season", mTvName.getText());
                v.getContext().startActivity(myIntent);
            }
        });
    }
}

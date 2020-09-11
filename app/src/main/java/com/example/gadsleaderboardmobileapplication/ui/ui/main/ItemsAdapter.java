package com.example.gadsleaderboardmobileapplication.ui.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboardmobileapplication.R;
import com.example.gadsleaderboardmobileapplication.datamodels.HoursModel;
import com.example.gadsleaderboardmobileapplication.datamodels.ScoreModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {
    private String itemType;
    private FragmentActivity activity;

    public void setScoresModelArrayList(ArrayList<ScoreModel> ScoresModelArrayList) {
        this.ScoresModelArrayList = ScoresModelArrayList;
        notifyDataSetChanged();
    }

    public ItemsAdapter(FragmentActivity _activity, String type) {
        this.itemType = type;
        this.activity = _activity;
    }

    public void setHoursModelArrayList(ArrayList<HoursModel> hoursModelArrayList) {
        this.hoursModelArrayList = hoursModelArrayList;
        notifyDataSetChanged();
    }

    private ArrayList<ScoreModel> ScoresModelArrayList = new ArrayList<>();
    private ArrayList<HoursModel> hoursModelArrayList = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        vInitRowData(holder, position);
    }

    private void vInitRowData(MyViewHolder holder, int position) {
        switch (itemType) {
            case "HOURS":
                Picasso.with(activity)
                        .load(hoursModelArrayList.get(position).getBadgeUrl())
                        .into(holder.ic_image);
                holder.txtview_name.setText(hoursModelArrayList.get(position).getName());
                holder.txtview_score.setText(String.format("%s learning hours, %s.", hoursModelArrayList.get(position).getHours(), hoursModelArrayList.get(position).getCountry()));
                break;
            case "SCORES":
                RelativeLayout.LayoutParams ic_imageLayoutParams =
                        (RelativeLayout.LayoutParams) holder.ic_image.getLayoutParams();
                ic_imageLayoutParams.setMargins(
                        (int) activity.getResources().getDimension(R.dimen._5sdp),
                        (int) activity.getResources().getDimension(R.dimen._5sdp)
                        , (int) activity.getResources().getDimension(R.dimen._10sdp),
                        (int) activity.getResources().getDimension(R.dimen._5sdp));

                Picasso.with(activity)
                        .load(ScoresModelArrayList.get(position).getBadgeUrl())
                        .into(holder.ic_image);
                holder.txtview_name.setText(ScoresModelArrayList.get(position).getName());
                holder.txtview_score.setText(String.format("%s skill IQ Score, %s.", ScoresModelArrayList.get(position).getScore(), ScoresModelArrayList.get(position).getCountry()));

                break;
        }

    }

    @Override
    public int getItemCount() {
        switch (itemType) {
            case "HOURS":
                return hoursModelArrayList.size();
            case "SCORES":
                return ScoresModelArrayList.size();
            default:
                return 0;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ic_image;
        AppCompatTextView txtview_name, txtview_score;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ic_image = itemView.findViewById(R.id.ic_image);
            txtview_name = itemView.findViewById(R.id.txtview_name);
            txtview_score = itemView.findViewById(R.id.txtview_score);
        }
    }
}

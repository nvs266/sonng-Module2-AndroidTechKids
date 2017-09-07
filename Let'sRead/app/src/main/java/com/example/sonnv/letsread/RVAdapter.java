package com.example.sonnv.letsread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SONNV on 9/7/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.StoryViewHolder> {

    public static class StoryViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tvTitle;
        TextView tvAuthor;
        ImageView ivStory;

        public StoryViewHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cv);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            ivStory = itemView.findViewById(R.id.iv_story);

        }
    }

    List<StoryModel> storyModelList;

    RVAdapter(List<StoryModel> storyModelList) {
        this.storyModelList = storyModelList;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        StoryViewHolder svh = new StoryViewHolder(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        holder.tvAuthor.setText(storyModelList.get(position).getAuthor());
        holder.tvTitle.setText(storyModelList.get(position).getTitle());

        String encodedString = storyModelList.get(position).getImage();
        String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",")  + 1);
        byte[] decodedString = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.ivStory.setImageBitmap(decodedByte);
    }


    @Override
    public int getItemCount() {
        return storyModelList.size();
    }
}

package com.example.appp;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private ArrayList<ReviewData> reviewsList;
    private Context context;

    ReviewAdapter(ArrayList<ReviewData> reviewsList, Context context) {
        this.reviewsList = reviewsList;
        this.context = context;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_row, parent, false);
        ReviewHolder reviewHolder = new ReviewHolder(item);
        return reviewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {

        final ReviewData review = reviewsList.get(position);// review object
        holder.author.setText(review.getAuthor());
        holder.content.setText(review.getContent());

    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    class ReviewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView content;

        public ReviewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}

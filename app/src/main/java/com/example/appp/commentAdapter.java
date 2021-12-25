package com.example.appp;

import android.content.DialogInterface;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Console;
import java.util.ArrayList;


public class commentAdapter extends RecyclerView.Adapter<commentAdapter.commentViewHolder> {
    ArrayList<comment> comments;
    DatabaseReference db;

    public commentAdapter(ArrayList<comment> comments) {
        this.comments = comments;
        db = FirebaseDatabase.getInstance().getReference().child("comments");
    }

    @Override
    public commentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row, parent, false);
        commentViewHolder holder = new commentViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(commentViewHolder holder, int position) {
        final comment comment = comments.get(position);
        holder.name.setText(comment.getUser());
        holder.comment.setText(comment.getComment());
        if (comment.isOwn()) {
            holder.containerlayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    db.child(comment.getComment_id()).removeValue();
                                    Log.d("Enter", comment.getComment_id());
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    Log.d("Enter", comment.getComment_id());
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(comment.getContext());
                    builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class commentViewHolder extends RecyclerView.ViewHolder {
        TextView name, comment;
        LinearLayout containerlayout;

        public commentViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameComment);
            comment = (TextView) itemView.findViewById(R.id.commentText);
            containerlayout = (LinearLayout) itemView.findViewById(R.id.containerlayout);
        }
    }
}

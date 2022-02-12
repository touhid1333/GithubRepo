package com.touhid.githubrepo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;
import com.touhid.githubrepo.R;
import com.touhid.githubrepo.pojo.commitsresponse.CommitsResponse;
import com.touhid.githubrepo.utils.ChildClickListner;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.CommitsViewHolder>{

    private Context context;
    private List<CommitsResponse> commits = new ArrayList<>();
    private ChildClickListner childClickListner;

    public CommitsAdapter(Context context, List<CommitsResponse> commits, ChildClickListner childClickListner) {
        this.context = context;
        this.commits = commits;
        this.childClickListner = childClickListner;
    }

    @NonNull
    @Override
    public CommitsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_commits, parent, false);
        return new CommitsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommitsViewHolder holder, int position) {

        if (commits != null){

            //author name init
            if (commits.get(position).getCommit().getAuthor().getName() != null) {
                String userFullName = commits.get(position).getCommit().getAuthor().getName();
                holder.commitUserNameTV.setText(userFullName);
            }

            //author avatar init
            if (commits.get(position).getAuthor().getAvatarUrl() != null) {
                String userAvatar = commits.get(position).getAuthor().getAvatarUrl();
                Glide.with(context)
                        .load(userAvatar)
                        .override(20, 20)
                        .into(holder.commitUserAvatarIV);
            }

            //commit message init
            if (commits.get(position).getCommit().getMessage() != null) {
                String commitTitle = commits.get(position).getCommit().getMessage();
                holder.commitTitleTV.setText(commitTitle);
            }

            //date time init
            if (commits.get(position).getCommit().getAuthor().getDate() != null) {
                String commitDateString = commits.get(position).getCommit().getAuthor().getDate();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                    ZonedDateTime date1 = ZonedDateTime.parse(commitDateString);
                    ZonedDateTime date2 = ZonedDateTime.now();
                    long diff = ChronoUnit.HOURS.between(date1, date2);
                    if (diff < 24) {
                        holder.commitTimeTV.setText(diff + " hours ago");
                    } else {
                        DateTimeFormatter inputFormatter = null;
                        inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
                        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault());
                        LocalDate date = LocalDate.parse(commitDateString, inputFormatter);
                        String formattedDate = outputFormatter.format(date);
                        holder.commitTimeTV.setText(String.valueOf(formattedDate));
                    }
                }
            }

        }

    }

    @Override
    public int getItemCount() {
        return commits.size();
    }

    class CommitsViewHolder extends RecyclerView.ViewHolder{

        MaterialTextView commitTitleTV, commitUserNameTV, commitTimeTV;
        CircleImageView commitUserAvatarIV;

        public CommitsViewHolder(@NonNull View itemView) {
            super(itemView);

            commitTitleTV = itemView.findViewById(R.id.commits_title_tv);
            commitUserNameTV = itemView.findViewById(R.id.commit_user_name);
            commitTimeTV = itemView.findViewById(R.id.commits_time_tv);
            commitUserAvatarIV = itemView.findViewById(R.id.commit_user_avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    childClickListner.onChildClick(view, getAdapterPosition());
                }
            });
        }
    }
}

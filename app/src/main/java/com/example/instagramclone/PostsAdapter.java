package com.example.instagramclone;

import android.content.Context;
import android.media.Image;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProfilePicture;
        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfilePicture = itemView.findViewById(R.id.ivProfilePicture);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
        }

        public void bind(Post post) {
            ParseFile profileImage = post.getUser().getParseFile("profilePicture");
            if (profileImage != null) {
                Glide.with(context).load(post.getUser().getParseFile("profilePicture").getUrl()).into(ivProfilePicture);
            }

            SpannableStringBuilder descriptionString = new SpannableStringBuilder(post.getUser().getUsername() + " " + post.getDescription());
            descriptionString.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, post.getUser().getUsername().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            tvDescription.setText(post.getUser().getUsername() + " " + post.getDescription());
            tvDescription.setText(descriptionString);

            tvUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if (image != null)
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
            Date date = post.getPostCreatedAt();
            tvTimestamp.setText(DateUtils.getRelativeTimeSpanString(date.getTime()));
        }
    }
}

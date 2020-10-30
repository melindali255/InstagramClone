package com.example.instagramclone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsGalleryAdapter extends RecyclerView.Adapter<PostsGalleryAdapter.ViewHolder>{
    private Context context;
    private List<Post> posts;

    public PostsGalleryAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostsGalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gallery_post, parent, false);
        return new PostsGalleryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsGalleryAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivGalleryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGalleryImage = itemView.findViewById(R.id.ivGalleryImage);
        }

        public void bind(Post post) {
            ParseFile image = post.getImage();
            if (image != null)
                Glide.with(context).load(post.getImage().getUrl()).into(ivGalleryImage);
        }
    }
}

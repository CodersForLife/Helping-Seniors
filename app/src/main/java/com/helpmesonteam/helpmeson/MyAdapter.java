package com.helpmesonteam.helpmeson;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.helpmeson.R;

/**
 * Created by dell on 7/1/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewholder> {

    private String[] dataset;
    public static class viewholder extends RecyclerView.ViewHolder{

        public ImageView authors_image;
        public TextView quote,author,category;
        public viewholder(View itemView) {
            super(itemView);
            authors_image=(ImageView)itemView.findViewById(R.id.authors_image);
            quote=(TextView)itemView.findViewById(R.id.quote);
            author=(TextView)itemView.findViewById(R.id.author);
            category=(TextView)itemView.findViewById(R.id.category);
        }
    }
    public MyAdapter(String[] dataset){
        this.dataset=dataset;
    }
    @Override
    public MyAdapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vx= LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_item,parent,false);
        viewholder vh=new viewholder(vx);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.viewholder holder, int position) {
        holder.category.setText(dataset[position]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }
}

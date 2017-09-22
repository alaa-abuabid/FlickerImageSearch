package com.example.alaa_ab.flickerimagesearch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Serializable
{
    private  ArrayList<Images> imagesArrayList ;

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image ;
        public ViewHolder(View itemView)
        {
            super(itemView);
           image = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }

    public Adapter(ArrayList images)
    {
        this.imagesArrayList = images;
    }
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemview = null;
        itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list,parent,false);
        return  new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.image.setImageBitmap(imagesArrayList.get(position).getBitmap());
    }

    @Override
    public int getItemCount()
    {
        return imagesArrayList.size();
    }

    @Override
    public int getItemViewType(int position)
    {


        return position;
    }

    public void setImagesArrayList(ArrayList<Images> imagesArrayList)
    {
        this.imagesArrayList = imagesArrayList;
    }

    public ArrayList<Images> getImagesArrayList() {
        return imagesArrayList;
    }
}

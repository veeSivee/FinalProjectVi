package com.iak.vi.finalprojectvi.main;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iak.vi.finalprojectvi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User_PC on 20/11/2016.
 */

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ItemHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> listImageUrl;

    public ListMovieAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        listImageUrl = new ArrayList<>();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView itemCard = (CardView)layoutInflater.inflate(R.layout.card_image_movie,parent,false);
        return new ItemHolder(itemCard);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        //holder.setItemText(listImageUrl.get(position));
        holder.setItemImage(listImageUrl.get(position));
    }

    @Override
    public int getItemCount() {
        return listImageUrl.size();
    }


    public void addItem(int location,String pathUrl){
        listImageUrl.add(location,pathUrl);
        notifyItemInserted(location);
    }

    public void clearAllItem(){
        listImageUrl.clear();
        notifyDataSetChanged();
    }


    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CardView card_1;

        ImageView img_cv;
        TextView tv_cv;

        public ItemHolder(CardView itemView) {
            super(itemView);
            card_1 = itemView;

            img_cv = (ImageView)itemView.findViewById(R.id.ivMoviePoster);
            tv_cv = (TextView)itemView.findViewById(R.id.tvLink);

            card_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public void onClick(View view) {

        }

        public void setItemImage(String imagePath){
            Picasso.with(context)
                    .load(imagePath)
                    .fit()
                    .into(img_cv);
        }

        public void setItemText(String value){
            tv_cv.setText(value);
        }

    }
}

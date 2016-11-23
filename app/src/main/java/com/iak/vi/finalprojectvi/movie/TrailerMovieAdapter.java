package com.iak.vi.finalprojectvi.movie;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iak.vi.finalprojectvi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taufiqotulfaidah on 11/23/16.
 */

public class TrailerMovieAdapter extends RecyclerView.Adapter<TrailerMovieAdapter.ItemHolder>{

    private LayoutInflater layoutInflater;
    private List<String> listTitleTrailer;
    private List<String> listPathTrailer;

    public TrailerMovieAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
        listTitleTrailer = new ArrayList<>();
        listPathTrailer = new ArrayList<>();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView itemCard = (CardView)layoutInflater.inflate(R.layout.card_movie_trailer,parent,false);
        return new ItemHolder(itemCard);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.setTitleTrailer(listTitleTrailer.get(position),
                listPathTrailer.get(position));
    }

    @Override
    public int getItemCount() {
        return listTitleTrailer.size();
    }

    public void addItem(int location, String titleTrailer, String pathTrailer){
        listTitleTrailer.add(location, titleTrailer);
        listPathTrailer.add(location, pathTrailer);
        notifyItemInserted(location);
    }

    public void clearAllItem() {
        listTitleTrailer.clear();
        listPathTrailer.clear();
        notifyDataSetChanged();
    }


    public class ItemHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView tvTitleTrailer;

        public ItemHolder(CardView itemView){
            super(itemView);

            this.cardView = itemView;

            init(itemView);
        }

        private void init(CardView itemView){
            tvTitleTrailer = (TextView) itemView.findViewById(R.id.tv_title_trailer);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public void setTitleTrailer(String titleTrailer, String pathUrlTrailer){
            tvTitleTrailer.setText(titleTrailer);
        }
    }
}

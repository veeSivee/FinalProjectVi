package com.iak.vi.finalprojectvi.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iak.vi.finalprojectvi.R;
import com.iak.vi.finalprojectvi.data.PopularMovie;
import com.iak.vi.finalprojectvi.movie.DetailMovie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taufiqotulfaidah on 20/11/2016.
 */

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ItemHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> listImageUrl;
    private List<String> listTitle;
    private List<PopularMovie> popularMovies;
    String[] listFavoriteMovie;

    private ListMovieContract.View viewContext;

    public ListMovieAdapter(Context context, ListMovieContract.View view){
        this.context = context;
        this.viewContext = view;
        layoutInflater = LayoutInflater.from(context);
        listImageUrl = new ArrayList<>();
        listTitle = new ArrayList<>();
        popularMovies = new ArrayList<>();
        listFavoriteMovie = null;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView itemCard = (CardView)layoutInflater.inflate(R.layout.card_image_movie,parent,false);
        return new ItemHolder(itemCard);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.setItemText(listTitle.get(position));
        holder.setItemImage(listImageUrl.get(position));
        if(popularMovies.size()>0){
            holder.setPopularMovie(popularMovies.get(position));
            holder.setColorFavorite(popularMovies.get(position).isFavorite());
        }
    }

    @Override
    public int getItemCount() {
        return listImageUrl.size();
    }


    public void addItem(int location, String path1, PopularMovie popularMovie){
        listImageUrl.add(location,path1 + popularMovie.getPosterPath());
        listTitle.add(location,popularMovie.getTitle());

        if(listFavoriteMovie != null){
            for (String idFav : listFavoriteMovie){
                if(idFav.equals(String.valueOf(popularMovie.getId()))){
                    popularMovie.setFavorite(true);
                }
            }
        }

        popularMovies.add(popularMovie);
        notifyItemInserted(location);
    }

    public void setIdFavoritemovie(String favList){

        if(!TextUtils.isEmpty(favList)){
            listFavoriteMovie = favList.split(",");
        }
    }

    public void clearAllItem(){
        listImageUrl.clear();
        listTitle.clear();
        popularMovies.clear();
        notifyDataSetChanged();
    }


    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CardView card_1;

        private ImageView ivPoster, ivFavorite;
        private TextView tv_cv;
        private PopularMovie popularMovie;

        public ItemHolder(CardView itemView) {
            super(itemView);
            card_1 = itemView;

            ivPoster = (ImageView)itemView.findViewById(R.id.ivMoviePoster);
            ivFavorite = (ImageView) itemView.findViewById(R.id.iv_favorite);
            tv_cv = (TextView)itemView.findViewById(R.id.tvLink);

            ivFavorite.setTag(R.drawable.heart_black);
            ivFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeColor(view);
                }
            });

            card_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle b = new Bundle();
                    b.putParcelable("movie",popularMovie);
                    Intent intent = new Intent(context, DetailMovie.class);
                    intent.putExtras(b);
                    context.startActivity(intent);
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
                    .into(ivPoster);
        }

        public void setItemText(String value){
            tv_cv.setText(value);
        }

        public void setPopularMovie(PopularMovie popularMovie){
            this.popularMovie = popularMovie;
        }

        private void changeColor(View view){
            ImageView imageView = (ImageView) view;

            Integer integer = (Integer) imageView.getTag();
            integer = integer == null ? 0 : integer;
            switch (integer){
                case R.drawable.heart_black:
                    imageView.setImageResource(R.drawable.heart_red);
                    imageView.setTag(R.drawable.heart_red);
                    viewContext.onAddFavoriteMovie(popularMovie);
                    break;

                case R.drawable.heart_red:
                    imageView.setImageResource(R.drawable.heart_black);
                    imageView.setTag(R.drawable.heart_black);
                    viewContext.onRemoveFavoriteMovie(popularMovie);
                    break;
            }

        }

        public void setColorFavorite(boolean isFavorite){

            if(isFavorite){
                ivFavorite.setImageResource(R.drawable.heart_red);
                ivFavorite.setTag(R.drawable.heart_red);
            }else {
                ivFavorite.setImageResource(R.drawable.heart_black);
                ivFavorite.setTag(R.drawable.heart_black);
            }
        }

    }
}

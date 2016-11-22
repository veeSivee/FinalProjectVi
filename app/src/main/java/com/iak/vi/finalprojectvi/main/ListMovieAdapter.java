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
import com.iak.vi.finalprojectvi.data.PopularMovie;
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
    private List<String> listTitle;
    private List<PopularMovie> popularMoviesFavorite;
    private List<PopularMovie> popularMovies;

    public ListMovieAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        listImageUrl = new ArrayList<>();
        listTitle = new ArrayList<>();
        popularMovies = new ArrayList<>();
        popularMoviesFavorite = new ArrayList<>();
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
        holder.setPopularMovie(popularMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return listImageUrl.size();
    }


    public void addItem(int location, String path1, PopularMovie popularMovie){
        listImageUrl.add(location,path1 + popularMovie.getPosterPath());
        listTitle.add(location,popularMovie.getTitle());
        popularMovies.add(popularMovie);
        notifyItemInserted(location);
    }

    public void clearAllItem(){
        listImageUrl.clear();
        listTitle.clear();
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
                    popularMoviesFavorite.add(popularMovie);
                    break;

                case R.drawable.heart_red:
                    imageView.setImageResource(R.drawable.heart_black);
                    imageView.setTag(R.drawable.heart_black);

                    for (int i = 0; i < popularMoviesFavorite.size(); i++) {
                         if(popularMovie.getId() == popularMoviesFavorite.get(i).getId()){
                             popularMoviesFavorite.remove(i);
                         }
                    }
                    break;
            }

        }

    }
}

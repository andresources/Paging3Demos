package com.paging.ex2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.paging.R
import com.paging.databinding.AdapterMovieBinding
import com.paging.ex1.loadImage
import com.paging.ex3.adapter.MainListAdapter

class MoviePagerAdapter : PagingDataAdapter<Movie,MoviePagerAdapter.MovieViewHolder> (MovieComparator){
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var name: TextView = view.findViewById(R.id.name)
        var imageview: ImageView = view.findViewById(R.id.imageview)
    }

    object MovieComparator: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            // Id is unique.
            return oldItem.original_title == newItem.original_title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)!!
        holder.name.text = "T : ${movie.original_title}"
        holder.imageview.loadImage("https://image.tmdb.org/t/p/w300"+movie.poster_path) //Extension fun.
        //Glide.with(holder.imageview.context).load("https://image.tmdb.org/t/p/w300"+movie.poster_path).into(holder.imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MoviePagerAdapter.MovieViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.adapter_movie, parent, false)
        )
    }
}
package com.anggadps.mymoviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.anggadps.mymoviedb.databinding.ItemMovieBinding
import com.anggadps.mymoviedb.models.Movie
import com.anggadps.mymoviedb.services.MovieItemClickListener

class MovieAdapter(private val movieItemClickListener: MovieItemClickListener, private val isUpcoming: Boolean) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie, isUpcoming)
        holder.itemView.setOnClickListener {
            movieItemClickListener.onMovieItemClick(movie)
        }
    }

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, isUpcoming: Boolean) {

            binding.imageViewRating.visibility = if (isUpcoming) View.GONE else View.VISIBLE //for set visibility star icon

            binding.textViewTitle.text = movie.title
            binding.textViewOverview.text = movie.vote_average
            if (isUpcoming){
                binding.textViewOverview.text = movie.release_date
            } else {
                val formattedVoteAverage = String.format("%.1f", movie.vote_average.toDouble())
                binding.textViewOverview.text = formattedVoteAverage
            }
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(binding.imageViewPoster)
        }
    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}

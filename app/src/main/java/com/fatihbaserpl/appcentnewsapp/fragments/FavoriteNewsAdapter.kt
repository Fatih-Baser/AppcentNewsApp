package com.fatihbaserpl.appcentnewsapp.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fatihbaserpl.appcentnewsapp.R
import com.fatihbaserpl.appcentnewsapp.databinding.FavoritelistitemBinding
import com.fatihbaserpl.appcentnewsapp.databinding.ListItemBinding
import com.fatihbaserpl.appcentnewsapp.domain.Article

class FavoriteNewsAdapter : RecyclerView.Adapter<FavoriteNewsAdapter.NewsHolder>(){

    inner class NewsHolder(val binding: FavoritelistitemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.apply {
                cardNews.startAnimation(
                    android.view.animation.AnimationUtils.loadAnimation(
                        binding.root.context,
                        R.anim.rv_animation
                    )
                )
                txtTitle.text = article.title

                val imageLink = article.urlToImage
                imgNews.load(imageLink) {
                    crossfade(true)
                    crossfade(1000)
                    transformations(RoundedCornersTransformation(25f))
                }
            }
        }
    }
    private val diffUtil = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var news: List<Article>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(
            FavoritelistitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: NewsHolder, position: Int) {

        val newsList = news[position]
        holder.bind(newsList)

        holder.binding.root.setOnClickListener { view ->
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(newsList)
            Navigation.findNavController(view).navigate(action)
        }
    }
    override fun getItemCount() = news.size
}
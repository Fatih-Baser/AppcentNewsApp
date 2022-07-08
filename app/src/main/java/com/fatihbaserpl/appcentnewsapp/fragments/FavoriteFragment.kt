package com.fatihbaserpl.appcentnewsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatihbaserpl.appcentnewsapp.NewsAdapter
import com.fatihbaserpl.appcentnewsapp.R
import com.fatihbaserpl.appcentnewsapp.databinding.FragmentFavoriteBinding
import com.fatihbaserpl.appcentnewsapp.databinding.FragmentNewsBinding
import com.fatihbaserpl.appcentnewsapp.favoriteroom.ArticleDatabase
import com.fatihbaserpl.appcentnewsapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private var favoriteNewsAdapter = FavoriteNewsAdapter()

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
         observeLiveData()


    }


    fun observeLiveData() {
        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer {
            it?.let {

                favoriteNewsAdapter.news = it
            }

        })

    }


    private fun setUpRecyclerView() {
        binding.favoriteRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteNewsAdapter
            setRecycledViewPool(RecyclerView.RecycledViewPool())
            setHasFixedSize(true)
        }
    }


}

package com.fatihbaserpl.appcentnewsapp.fragments

import android.os.Bundle
import android.view.View

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fatihbaserpl.appcentnewsapp.databinding.FragmentFavoriteBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private var favoriteNewsAdapter = FavoriteNewsAdapter()


    private val viewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
         observeLiveData()

        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val articles =favoriteNewsAdapter.news[position]

                if (articles != null) {
                    viewModel.deleteFavorite(articles)
                }
                view.let {
                    Snackbar.make(it,"Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                        setAction("Undo"){
                            if (articles != null) {
                                viewModel.AddFavorite(articles)
                            }
                        }
                        show()
                    }
                }
            }


        }
        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.favoriteRv)
        }


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

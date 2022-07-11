package com.fatihbaserpl.appcentnewsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.setViewTreeOnBackPressedDispatcherOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fatihbaserpl.appcentnewsapp.R
import com.fatihbaserpl.appcentnewsapp.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()

    //   private val viewModel:FavoriteViewModel by viewModels()

    private val viewModel: FavoriteViewModel by lazy {
        ViewModelProvider(this)[FavoriteViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        backStack()
        bindIncomingData()

        val articles = args.newsArgs
        binding.favoriteButton.setOnClickListener(View.OnClickListener {
            if (articles != null) {
                viewModel.AddFavorite(articles)
            }
            view.let {
                Snackbar.make(it, "Successfully added to favorite", Snackbar.LENGTH_LONG)
                    .apply {
                        show()
                    }
            }
        })
        binding.backButton.setOnClickListener(View.OnClickListener {
            backStack()
            this.findNavController().popBackStack()
        })

        binding.shareButton.setOnClickListener(View.OnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, articles!!.url);
            startActivity(Intent.createChooser(shareIntent,getString(R.string.app_name)))
        })

    }


    private fun bindIncomingData() {

        val article = args.newsArgs

        if (article != null) {
            binding.txtTitle.text = article.title
        }
        if (article != null) {
            binding.txtDescription.text = article.description
        }
        if (article != null) {
            binding.txtContent.text = article.content
        }
        if (article != null) {
            binding.txtAutor.text = article.author
        }
        if (article != null) {
            binding.txtPublish.text = article.publishedAt
        }
        if (article != null) {
            binding.imgNews.load(article.urlToImage) {
                crossfade(true)
                crossfade(500)
                transformations(RoundedCornersTransformation(25f))
            }
        }
        binding.btnRead.setOnClickListener {
            val action = article?.let { web ->
                DetailFragmentDirections.actionDetailFragmentToWebViewFragment(
                    web
                )
            }
            if (action != null) {
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    private fun backStack() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
}
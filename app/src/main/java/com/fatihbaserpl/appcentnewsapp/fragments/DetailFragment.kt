package com.fatihbaserpl.appcentnewsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.RoundedCornersTransformation
import com.fatihbaserpl.appcentnewsapp.R
import com.fatihbaserpl.appcentnewsapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        backStack()
        bindIncomingData()
    }

    private fun bindIncomingData() {

        val article = args.newsArgs

        if (article != null) {
            binding.txtDescription.text = article.description
        }
        if (article != null) {
            binding.txtContent.text = article.content
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
package com.example.imagegallery.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.imagegallery.R
import com.example.imagegallery.databinding.DialogFragmentImageInfoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ImageInfoDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogFragmentImageInfoBinding
    private val args: ImageInfoDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogFragmentImageInfoBinding.inflate(inflater, container, false)

        setUi()

        return binding.root
    }

    /**
     * UI configuration.
     */
    private fun setUi() {
        val image = args.image

        with(binding) {
            imageSubreddit.text = getString(R.string.subreddit_name_prefixed, image.subreddit)
            imageTitle.text = image.title
        }
    }
}
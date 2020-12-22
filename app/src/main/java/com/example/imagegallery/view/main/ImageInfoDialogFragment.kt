package com.example.imagegallery.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.imagegallery.R
import com.example.imagegallery.app.toast
import com.example.imagegallery.databinding.DialogFragmentImageInfoBinding
import com.example.imagegallery.viewmodel.ImageInfoViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ImageInfoDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: DialogFragmentImageInfoBinding
    private val args: ImageInfoDialogFragmentArgs by navArgs()

    private val imageInfoViewModel: ImageInfoViewModel by viewModel { parametersOf(args.image) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<DialogFragmentImageInfoBinding>(
            inflater, R.layout.dialog_fragment_image_info, container, false
        ).apply {
            viewModel = imageInfoViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        setUi()
        setButtonsClickListener()

        return binding.root
    }

    /**
     * UI configuration.
     */
    private fun setUi() {
        val image = imageInfoViewModel.image

        with(binding) {
            imageSubreddit.text = getString(R.string.subreddit_name_prefixed, image.subreddit)
            imageTitle.text = image.title
        }
    }

    /**
     * Buttons click listener configuration.
     */
    private fun setButtonsClickListener() {
        binding.saveButton.setOnClickListener {
            // Add image to favorites
            imageInfoViewModel.saveImage()

            toast(R.string.added_to_favorites)
            this@ImageInfoDialogFragment.dismiss()
        }

        binding.removeButton.setOnClickListener {
            // Remove image from favorites
            imageInfoViewModel.removeImage()

            toast(R.string.removed_from_favorites)
            this@ImageInfoDialogFragment.dismiss()
        }
    }
}

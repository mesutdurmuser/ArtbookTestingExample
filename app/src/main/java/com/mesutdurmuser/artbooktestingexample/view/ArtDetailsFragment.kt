package com.mesutdurmuser.artbooktestingexample.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mesutdurmuser.artbooktestingexample.R
import com.mesutdurmuser.artbooktestingexample.databinding.FragmentArtsDetailsBinding

class ArtDetailsFragment : Fragment(R.layout.fragment_arts_details) {

    private var fragmentBinding : FragmentArtsDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtsDetailsBinding.bind(view)
        fragmentBinding = binding

        binding.artImageView.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }

        //burada geri tuşuna bastığımızda ne olacağını söyleyeceğimiz yerlerde kullanacağımız değişkenimizi tanımlıyoruz
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                //popbackstack bir önceki stack e dönmek ve bu fragmenti kapat demek için kullanılır.
                findNavController().popBackStack()
            }
        }

        //burada android x ile gelen onBackPressedDispatcher yardımcısı ile yukarıda tanımladığımız call back i kullanıyoruz
        requireActivity().onBackPressedDispatcher.addCallback(callback)


    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }


}
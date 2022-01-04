package com.mesutdurmuser.artbooktestingexample.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mesutdurmuser.artbooktestingexample.R
import com.mesutdurmuser.artbooktestingexample.databinding.FragmentArtsBinding

class ArtFragment : Fragment(R.layout.fragment_arts) {

    //FragmentArtsBinding sayesinde art_fragment'e erişim sağlayacağız burada boş olarak bindingimizi oluşturuyoruz.
    private var fragmentBinding : FragmentArtsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //yukarıda boş olarak oluşturduğumuz bindingimizin içini dolduruyoruz.
        val binding = FragmentArtsBinding.bind(view)
        fragmentBinding = binding

        //binding aracılığı ile FloatingActionButtona ulaşıyoruz ve setOnclick listener ile tıklandığında yapması gerekeni söylüyoruz.
        binding.fab.setOnClickListener {
            //Navigationda tanımladığımız ArtDetailsFragment'e geçiş işlemimizi gerçekleştiriyoruz.
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())

        }

    }

    override fun onDestroyView() {
        //Uygulamamız destroy olduğunda (kapandığında) fragment bindingimizi null'a eşitliyoruz. Bu bindingin sağlıklı kullanım yöntemidir.
        fragmentBinding = null
        super.onDestroyView()
    }
}
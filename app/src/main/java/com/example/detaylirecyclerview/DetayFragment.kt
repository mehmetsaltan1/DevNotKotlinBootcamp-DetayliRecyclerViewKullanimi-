package com.example.detaylirecyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.detaylirecyclerview.databinding.FragmentDetayBinding
import com.google.android.material.snackbar.Snackbar


class DetayFragment : Fragment() {
    private lateinit var tasarim:FragmentDetayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tasarim = FragmentDetayBinding.inflate(inflater, container, false)
        val bundle:DetayFragmentArgs by navArgs()
        val gelenFilm = bundle.film
        tasarim.toolbarDetay.title = gelenFilm.filmAdi
        tasarim.ivFilmResim.setImageResource(resources.getIdentifier(gelenFilm.filmResimAdi,"drawable",requireContext().packageName))
        tasarim.tvFilmYonetmen.text=gelenFilm.filmYonetmen
        tasarim.tvFilmYil.text = gelenFilm.filmYil.toString()
        tasarim.tvFilmFiyat.text = "${gelenFilm.filmFiyat} ₺"
        tasarim.buttonSiparisVer.setOnClickListener {
            Snackbar.make(it,"${gelenFilm.filmAdi} sipariş verildi",Snackbar.LENGTH_SHORT).show()
        }
        return tasarim.root
    }
}
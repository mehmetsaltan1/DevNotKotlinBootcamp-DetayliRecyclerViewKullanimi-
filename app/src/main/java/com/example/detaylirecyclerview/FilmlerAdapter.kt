package com.example.detaylirecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class FilmlerAdapter(var mContext:Context,var filmlerListesi:List<Filmler>):RecyclerView.Adapter<FilmlerAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(view: View):RecyclerView.ViewHolder(view){
        var cardViewFilm:CardView
        var imageViewFilmResim:ImageView
        var textViewFilmAdi:TextView
        var textViewFilmFiyat:TextView
        var buttonKirala:Button
        var imageViewDahaFazla:ImageView
        init {
            cardViewFilm = view.findViewById(R.id.cardViewFilm)
            imageViewFilmResim = view.findViewById(R.id.imageViewFilmResim)
            textViewFilmAdi = view.findViewById(R.id.textViewFilmAdi)
            textViewFilmFiyat = view.findViewById(R.id.textViewFilmFiyat)
            buttonKirala = view.findViewById(R.id.buttonKirala)
            imageViewDahaFazla = view.findViewById(R.id.imageViewDahaFazla)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
       val tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val film = filmlerListesi.get(position)
        holder.imageViewFilmResim.setImageResource(
            mContext.resources.getIdentifier(film.filmResimAdi,"drawable",mContext.packageName))
        holder.textViewFilmAdi.text = film.filmAdi
        holder.textViewFilmFiyat.text = "${film.filmFiyat} ₺"
        holder.buttonKirala.setOnClickListener {
            Snackbar.make(it,"${film.filmAdi} kiralandı",Snackbar.LENGTH_SHORT).show()
        }
        holder.cardViewFilm.setOnClickListener {
        val gecis = AnasayfaFragmentDirections.detayGecis(film)
        Navigation.findNavController(it).navigate(gecis)
        }
        holder.imageViewDahaFazla.setOnClickListener {
            val popup = androidx.appcompat.widget.PopupMenu(mContext,it)
            popup.menuInflater.inflate(R.menu.popup_menu,popup.menu)
            popup.show()
            popup.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_haber_ver -> {
                        Snackbar.make(holder.imageViewDahaFazla,"${film.filmAdi} haberdar edilmek için eklendi",Snackbar.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_favori -> {
                        Snackbar.make(holder.imageViewDahaFazla,"${film.filmAdi} favorilere eklendi",Snackbar.LENGTH_SHORT).show()
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }



    }

    override fun getItemCount(): Int {
       return  filmlerListesi.size
    }
}
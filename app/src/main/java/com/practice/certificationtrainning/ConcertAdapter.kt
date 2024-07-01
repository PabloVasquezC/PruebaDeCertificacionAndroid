package com.practice.certificationtrainning

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.certificationtrainning.Model.Local.Entities.ConcertsEntity
import com.practice.certificationtrainning.databinding.ConcertsListBinding


class ConcertAdapter : RecyclerView.Adapter<ConcertAdapter.ConcertVH>(){

    //Referencias para el adapter
    private var listConcerts = listOf<ConcertsEntity>()
    private val selectedConcert = MutableLiveData<ConcertsEntity>()

    //Función para actualizar el adapter
    fun update(list: List<ConcertsEntity>){
        listConcerts = list
        notifyDataSetChanged()
    }

    //Función para seleccionar un curso
    fun selectedConcert(): LiveData<ConcertsEntity> = selectedConcert
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcertVH {
        return ConcertVH(ConcertsListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = listConcerts.size

    override fun onBindViewHolder(holder: ConcertVH, position: Int) {
        val concert = listConcerts[position]
        holder.bin(concert)
    }





    inner class ConcertVH(private val mBinding: ConcertsListBinding):
        RecyclerView.ViewHolder(mBinding.root), View.OnClickListener{

        fun bin(concert: ConcertsEntity){
            Glide.with(mBinding.imgConcert).load(concert.image).centerCrop().into(mBinding.imgConcert)


            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedConcert.value = listConcerts[adapterPosition]
            Log.d("OnClick", adapterPosition.toString())
        }

    }


}
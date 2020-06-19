package com.easyinc.weatherapp.ui.cities

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyinc.weatherapp.R
import com.easyinc.weatherapp.common.extentions.inflate
import kotlinx.android.synthetic.main.city_raw.view.*
import javax.inject.Inject

class CitiesAdapter @Inject constructor(): RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>(){

    var citiesList = listOf<String>()

    internal var deleteClickListener: (String) -> Unit = {_ -> }
    internal var editClickListener: (String) -> Unit = {_ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CitiesViewHolder(parent.inflate(R.layout.city_raw))

    override fun getItemCount() = citiesList.size

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) = holder.bind(citiesList[position],editClickListener,deleteClickListener)

    fun submitList(list: List<String>){
        citiesList = list
        notifyDataSetChanged()
    }

    inner class CitiesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(cityName: String,editClickListener: (String) -> Unit,deleteClickListener: (String) -> Unit){
            itemView.city_name.text = cityName

            itemView.edit_city.setOnClickListener {
                editClickListener(cityName)
            }
            itemView.delete_city.setOnClickListener {
                deleteClickListener(cityName)
            }
        }
    }
}
package com.easyinc.weatherapp.ui.cities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.easyinc.weatherapp.R
import com.easyinc.weatherapp.common.Logger
import com.easyinc.weatherapp.common.base.BaseFragment
import com.easyinc.weatherapp.common.util.Resource
import com.easyinc.weatherapp.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_cities.*
import javax.inject.Inject

class CitiesFragment : BaseFragment() {

    @Inject lateinit var adapter: CitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = (activity as MainActivity).mainViewModel
        mViewModel.getCitiesName()
    }

    override fun layoutId(): Int {
        return R.layout.fragment_cities
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        handleRecyclerView()
        observeCitesNames()
        onEditClickListener()
        onDeleteClickListener()

    }

    private fun handleRecyclerView(){
        cities_recycler.adapter = adapter
        cities_recycler.layoutManager = LinearLayoutManager(context)
        cities_recycler.setHasFixedSize(true)
    }

    private fun onDeleteClickListener() {
        adapter.deleteClickListener = {
            mViewModel.getCityToUpdate(it)
            observeCityToUpdate()
        }
    }

    private fun onEditClickListener() {
        adapter.editClickListener = {
            val bundle = bundleOf(resources.getString(R.string.city_name_argument)  to it)
            navController.navigate(R.id.action_citiesFragment_to_manageCityFragment,bundle)
        }
    }

    private fun observeCityToUpdate(){
        mViewModel.observeCityToUpdate().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> mViewModel.deleteCity(it.data!!)
                Resource.Status.ERROR -> {}
            }
        })
    }


    private fun observeCitesNames(){
        mViewModel.observeCitiesNames().observe(viewLifecycleOwner, Observer {
            Logger.dt("${it.data}")
            when(it.status){
                Resource.Status.SUCCESS -> adapter.submitList(it.data!!)
                Resource.Status.ERROR -> {}
            }
        })
    }


}
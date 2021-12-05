package com.project.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.app.R
import com.project.app.databinding.FragmentHomeBinding
import com.project.app.model.RecyclerList

class HomeFragment : Fragment() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initRecyclerView()
        initViewModel()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView(){
        //rvCountry
        _binding?.rvCountry?.layoutManager = LinearLayoutManager(activity)
        recyclerViewAdapter=RecyclerViewAdapter()
        _binding?.rvCountry?.adapter=recyclerViewAdapter
    }


    private fun initViewModel(){
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        //viewLifecycleOwner/ this
        homeViewModel.getLiveDataObserver().observe(viewLifecycleOwner, object: Observer<RecyclerList>{
            override fun onChanged(t: RecyclerList?) {
                if (t!=null){
                    recyclerViewAdapter.setUpdateList(t.items)
                    recyclerViewAdapter.notifyDataSetChanged()
                }else
                {
                    Toast.makeText(activity,"Error",Toast.LENGTH_LONG)

                }
            }

        })
        homeViewModel.makeApiCallFrutas()




        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


    }
}
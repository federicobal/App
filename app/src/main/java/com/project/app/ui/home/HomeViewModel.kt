package com.project.app.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.app.MainApplication
import com.project.app.di.ServiceInterface
//import com.project.app.model.Owner
import com.project.app.model.RecyclerData
import com.project.app.model.RecyclerList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeViewModel(application: Application) : AndroidViewModel(application) {


    @Inject
    lateinit var mService:ServiceInterface

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private lateinit var liveDataList: MutableLiveData<RecyclerList>

    init {
        //here we need to init application.
        (application as MainApplication).getServiceComponent().inject(this)
        liveDataList= MutableLiveData()
    }

    fun getLiveDataObserver():MutableLiveData<RecyclerList>
    {
        return liveDataList
    }

    fun makeApiCallFrutas(){
        var list:MutableList<RecyclerData> = mutableListOf<RecyclerData>()
        val value:RecyclerData = RecyclerData("1","manzana","https://www.eluniversal.com.mx/sites/default/files/2016/09/07/manzana.jpg")
        list.add(value)
        list.add(value)
        list.add(value)
        list.add(value)
        var rlist=RecyclerList(list)
        liveDataList.postValue(rlist)
    }
    fun makeApiCall(){
        val call: Call<RecyclerList>? = mService.getDataFromAPI("1")
        call?.enqueue(object :Callback<RecyclerList>{
            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
//                liveDataList.postValue(null)
            }

            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful )
                {
                    liveDataList.postValue(response.body())
                }else
                {
//                    liveDataList.postValue(null)
                }
            }
        })
    }
}
package com.project.app.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.project.app.MainApplication
import com.project.app.di.ServiceInterface
import com.project.app.model.RecyclerData
import com.project.app.model.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG ="HomeViewModel"
    @Inject
    lateinit var mService:ServiceInterface

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private lateinit var liveDataList: MutableLiveData<List<RecyclerData>>

//    private val _response = MutableLiveData<Resource<RecyclerData>>()
//    val response: LiveData<Resource<RecyclerData>> = _response

    init {
        //here we need to init application.
        (application as MainApplication).getServiceComponent().inject(this)
        liveDataList= MutableLiveData()
    }

    fun getLiveDataObserver():MutableLiveData<List<RecyclerData>>
    {
        return liveDataList
    }

    fun makeApiCall(){
//        val call: Call<List<RecyclerData>>? = mService.getDataFromAPI("1")
//        call?.enqueue(object :Callback<List<RecyclerData>>{
//            override fun onFailure(call: Call<List<RecyclerData>>, t: Throwable) {
////                liveDataList.postValue(null)
//            }
//
//            override fun onResponse(call: Call<List<RecyclerData>>, response: Response<List<RecyclerData>>) {
//                if (response.isSuccessful )
//                {
//                    liveDataList.postValue(response.body())
//                }else
//                {
////                    liveDataList.postValue(null)
//                }
//            }
//        })
        Log.d(TAG, "1")
//        _response.value = Resource.Loading
        viewModelScope.launch {
            try {
                var r = mService.getDataFromAPI("application/json","90","1")
                liveDataList.value = r!!.toList()
//                _response.value = uRR

            }
            catch (ex:Exception)
            {
//                _response.value = Resource.Failure(ex)
                Log.e(TAG,ex.message.toString())
            }
        }
    }
}
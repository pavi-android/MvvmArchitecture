package lookat.example.tushar.allstatemobile.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import lookat.example.tushar.allstatemobile.model.HerosModel
import lookat.example.tushar.allstatemobile.network.ServerConnection
import lookat.example.tushar.allstatemobile.network.WebService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class HerosRepository {

    fun getHerosList(): LiveData<List<HerosModel>>{

        val result =  MutableLiveData<List<HerosModel>>()
        ServerConnection().getConnection().getHeroes().enqueue(object : retrofit2.Callback<List<HerosModel>> {

            override fun onResponse(call: Call<List<HerosModel>>, response: Response<List<HerosModel>>) {
                result.value = response.body()
            }

            override fun onFailure(call: Call<List<HerosModel>>, t: Throwable) {
                Log.i("onFailure",""+t.stackTrace)
            }
        })

      return result
    }

}
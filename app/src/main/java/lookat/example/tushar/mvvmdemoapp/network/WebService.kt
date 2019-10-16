package lookat.example.tushar.allstatemobile.network

import lookat.example.tushar.allstatemobile.model.HerosModel
import retrofit2.Call
import retrofit2.http.GET



interface WebService {

    @GET("marvel")
    fun getHeroes(): Call<List<HerosModel>>

    companion object {
        val BASE_URL = "https://simplifiedcoding.net/demos/"
    }
}
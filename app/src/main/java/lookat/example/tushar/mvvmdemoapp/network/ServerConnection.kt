package lookat.example.tushar.allstatemobile.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerConnection {

    internal var retrofitRider = Retrofit.Builder()
        .baseUrl(WebService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getConnection(): WebService {
        return retrofitRider.create(WebService::class.java)

    }


}
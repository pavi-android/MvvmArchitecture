package lookat.example.tushar.allstatemobile.network

import android.util.Log
import lookat.example.tushar.mvvmdemoapp.uitl.MyApplication
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class ServerConnection {

    private val TAG = "ServerConnection"
    val HEADER_CACHE_CONTROL = "Cache-Control"
    val HEADER_PRAGMA = "Pragma"

    //5MB
    private var cacheSize = (5 * 1024 * 1024).toLong()

    internal var retrofitRider = Retrofit.Builder()
        .baseUrl(WebService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient())
        .build()

    fun getConnection(): WebService {
        return retrofitRider.create(WebService::class.java)

    }


    private fun cache(): Cache {
        return Cache(File(MyApplication.getInstance().cacheDir, "heroList"), cacheSize)
    }

    private fun okHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .addNetworkInterceptor(networkInterceptor())
            .addInterceptor(offlineInterceptor())
            .cache(cache())
            .build()
    }


    //This interceptor will be called both if the network is available and if the network is not available
    private fun offlineInterceptor(): Interceptor {

        return Interceptor { chain ->

            var request = chain.request()

            if (!MyApplication.hasNetwork()) {
                val cacheControl = CacheControl.Builder().maxStale(7, TimeUnit.DAYS).build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }

            chain.proceed(request)
        }

    }

    //This interceptor will be called ONLY if the network is available
    private fun networkInterceptor(): Interceptor {

        return Interceptor { chain ->

            val response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()

            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }

    }

    private fun httpLoggingInterceptor(): Interceptor {

        var httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d(TAG, "log: http log: $message")
            }
        })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor

    }

}
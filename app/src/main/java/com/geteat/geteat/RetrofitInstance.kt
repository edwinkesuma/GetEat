package com.geteat.geteat

import retrofit2.Retrofit
import com.geteat.geteat.utils.Constants.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {
    companion object{
        private val retrofit by lazy{
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }

        val api by lazy {
            retrofit.create(NotificationAPI::class.java)
        }
    }
}
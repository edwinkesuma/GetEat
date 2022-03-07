package com.geteat.geteat.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geteat.geteat.databinding.ActivityMainBinding
import com.geteat.geteat.utils.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val normalTypeface:Typeface = Typeface.createFromAsset(assets, "Montserrat-Regular.ttf")
//        binding.tvNormalText.typeface = normalTypeface
//
//        val boldTypeface:Typeface = Typeface.createFromAsset(assets, "Montserrat-Bold.ttf")
//        binding.tvNormalText.typeface = boldTypeface

        // Get username tersimpan di Android SharedPreferences.
        // Buat instance dari Android SharedPreferences
        val sharedPreferences =
                getSharedPreferences(Constants.MYSHOPPAL_PREFERENCES, Context.MODE_PRIVATE)

        val username = sharedPreferences.getString(Constants.LOGGED_IN_USERNAME, "")!!
        binding.tvMain.text= "The logged in user is $username."
    }
}
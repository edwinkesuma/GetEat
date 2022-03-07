package com.geteat.geteat.activities

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.geteat.geteat.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Update the background color of the action bar as per our design requirement.
        supportActionBar!!.setBackgroundDrawable(
                ContextCompat.getDrawable(
                        this@DashboardActivity,
                        R.drawable.app_gradient_color_background
                )
        )
        // END

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // TODO Step 7: Add the sold products navigation id in AppBarConfiguration.
        // START
        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.navigation_products,
                        R.id.navigation_dashboard,
                        R.id.navigation_orders,
                        R.id.navigation_sold_products,
                        R.id.navigation_online_wallet,
                )
        )
        // END
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        doubleBackToExit()
    }
}
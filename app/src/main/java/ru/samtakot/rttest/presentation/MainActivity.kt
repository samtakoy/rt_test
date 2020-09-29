package ru.samtakot.rttest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import ru.samtakot.rttest.R

class MainActivity : AppCompatActivity() {

    private val navController by lazy {
        Navigation.findNavController(
            this@MainActivity,
            R.id.nav_host_fragment
        )
    }
    private val appBarConfiguration by lazy { AppBarConfiguration.Builder(R.id.userList, R.id.about).build() }

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBar()
        setupBottomNavigationBar()
    }

    private fun setupActionBar() {
        //setSupportActionBar(main_toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottomNavigationBar() {
        bottom_navigation.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
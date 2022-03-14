package com.velvet.tarot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.velvet.tarot.fate.FateContract

class AppActivity : AppCompatActivity(), FateContract.Host {
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }
}
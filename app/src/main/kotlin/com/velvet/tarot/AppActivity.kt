package com.velvet.tarot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.velvet.tarot.ui.AppTheme
import com.velvet.tarot.ui.SystemUiSetup

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContent {
            AppTheme {
                SystemUiSetup()
                AppScreen()
            }
        }
    }
}
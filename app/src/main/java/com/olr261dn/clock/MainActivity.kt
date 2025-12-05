package com.olr261dn.clock

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.olr261dn.clock.navigation.ClockNavigation
import com.olr261dn.clock.ui.theme.ClockTheme
import com.olr261dn.clock.utils.checkFullScreenIntentsPermission.checkAndRequestFullScreenIntentsPermission
import com.olr261dn.clock.utils.sharedPreferences.PreferencesConstants.KEY_ACTIVITY_ACTIVE
import com.olr261dn.clock.utils.sharedPreferences.PreferencesConstants.KEY_APP_VERSION
import com.olr261dn.clock.utils.sharedPreferences.PreferencesConstants.PREFS_NAME
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            checkFullScreenIntentAfterUpdate()
        }
        super.onCreate(savedInstanceState)
        val screen = intent.getStringExtra("screen")

        enableEdgeToEdge()
        setContent {
            ClockTheme {
                ClockApp(screen=screen)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setActivityActive(true)
    }

    override fun onStop() {
        super.onStop()
        setActivityActive(false)
    }

    private fun setActivityActive(isActive: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(KEY_ACTIVITY_ACTIVE, isActive)
            apply()
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun checkFullScreenIntentAfterUpdate(){
        val savedVersionCode = sharedPreferences.getInt(KEY_APP_VERSION, -1)
        val currentVersionCode = BuildConfig.VERSION_CODE
        if (savedVersionCode != currentVersionCode) {
            sharedPreferences.edit().putInt(
                KEY_APP_VERSION, currentVersionCode).apply()
            if (savedVersionCode != -1) checkAndRequestFullScreenIntentsPermission()
        }
    }
}


@Composable
private fun ClockApp(screen: String?)
{
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize())
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            ClockNavigation(screen = screen)
        }

    }
}




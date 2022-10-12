package com.ssau.sunsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssau.sunsystem.ui.screen.PlanetSystemFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PlanetSystemFragment.newInstance())
                .commitNow()
        }
    }
}
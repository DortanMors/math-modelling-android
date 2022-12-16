package com.ssau.sunsystem

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.ssau.sunsystem.ui.screen.planets.PlanetsSetupFragment
import com.ssau.sunsystem.util.tag

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this) {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportFragmentManager.popBackStack()
            }
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PlanetsSetupFragment.newInstance())
                .addToBackStack(PlanetsSetupFragment.tag)
                .commit()
        }
    }
}

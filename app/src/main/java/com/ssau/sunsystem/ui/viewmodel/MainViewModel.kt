package com.ssau.sunsystem.ui.viewmodel

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.ssau.sunsystem.Defaults
import com.ssau.sunsystem.Defaults.DEFAULT_TIMESTEP
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.model.ApproximationMethod
import com.ssau.sunsystem.ui.screen.planets.CustomizedPlanet
import com.ssau.sunsystem.util.mapToScheme
import com.ssau.sunsystem.util.toSpaceBody
import com.ssau.sunsystemlib.core.Constants
import com.ssau.sunsystemlib.core.WorkspaceImpl
import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.core.interfaces.Workspace
import com.ssau.sunsystemlib.entity.SpaceBody
import kotlin.random.Random
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainViewModel : ViewModel() {
    private var scheme: Scheme = Defaults.scheme

    var customizedPlanets = Defaults.planetsUi

    var planets: List<SpaceBody> = emptyList()

    val planetsCount: Int
        get() = planets.size //todo hardcode

    fun setScheme(method: ApproximationMethod) {
        this.scheme = method.mapToScheme()
    }

    fun setupPlanets() {
        planets = customizedPlanets.map { it.toSpaceBody() }
    }

    fun createPlanet(lastPlanet: CustomizedPlanet?, name: String): CustomizedPlanet =
        CustomizedPlanet(
            name = name,
            color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
            x = lastPlanet?.run { x * 1.1 } ?: 0.0,
            mass = lastPlanet?.run { mass * 1.5 } ?: Constants.SUN_MASS,
        )

    private val workspace: Workspace by lazy {
        WorkspaceImpl( // todo from SettingsFragment
            planets = planets,
            scheme = scheme,
            timeStep = DEFAULT_TIMESTEP,
        ).apply {
            start()
        }
    }

    val uiState: Flow<List<SpaceBody>> by lazy {
        workspace.bodiesState.map { systemState ->
            systemState.currentState.bodies
        }
    }

}

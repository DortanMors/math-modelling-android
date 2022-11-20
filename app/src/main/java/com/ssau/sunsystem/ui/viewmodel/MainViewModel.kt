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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MainViewModel : ViewModel() {
    private var scheme: Scheme = Defaults.scheme

    var customizedPlanets = Defaults.planetsUi

    var planets: List<SpaceBody> = emptyList()

    var timeStep: Long = DEFAULT_TIMESTEP
        set(value) {
            field = value.also {
                workspace?.timeStep = it
            }
        }

    fun setScheme(method: ApproximationMethod) {
        this.scheme = method.mapToScheme().also {
            workspace?.scheme = it
        }
    }

    fun setupPlanets() {
        planets = customizedPlanets.map { it.toSpaceBody() }
        createWorkspace()
    }

    fun createPlanet(lastPlanet: CustomizedPlanet?, name: String): CustomizedPlanet =
        CustomizedPlanet(
            name = name,
            color = Color.rgb(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)),
            x = lastPlanet?.run { x * 1.1 } ?: 0.0,
            mass = lastPlanet?.run { mass * 1.5 } ?: Constants.SUN_MASS,
        )

    private var workspace: Workspace? = null

    fun start() {
        workspace?.start()
    }

    fun pause() {
        workspace?.pause()
    }

    private fun createWorkspace() {
        workspace = WorkspaceImpl(
            planets = planets,
        ).apply {
            scheme = this@MainViewModel.scheme
            timeStep = this@MainViewModel.timeStep
        }
    }

    val uiState: Flow<List<SpaceBody>>
    get() = workspace?.bodiesState?.map { systemState ->
        systemState.currentState.bodies
    } ?: flow { emptyList<SpaceBody>() }

}

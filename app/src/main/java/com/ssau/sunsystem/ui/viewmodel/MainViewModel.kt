package com.ssau.sunsystem.ui.viewmodel

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.ssau.sunsystem.Defaults
import com.ssau.sunsystem.ui.model.ApproximationMethod
import com.ssau.sunsystem.ui.model.UiSpaceBody
import com.ssau.sunsystem.util.mapToScheme
import com.ssau.sunsystem.util.mapToUi
import com.ssau.sunsystemlib.core.Constants.DEFAULT_TIMESTEP
import com.ssau.sunsystemlib.core.WorkspaceImpl
import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.core.interfaces.Workspace
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.util.Vector3d
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainViewModel : ViewModel() {
    private var scheme: Scheme = Defaults.scheme

    val planets: List<SpaceBody>
        get() = Defaults.planets

    val planetsCount: Int
        get() = planets.size //todo hardcode

    fun setScheme(method: ApproximationMethod) {
        this.scheme = method.mapToScheme()
    }

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

package com.ssau.sunsystem.ui.viewmodel

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.ssau.sunsystem.ui.model.UiSpaceBody
import com.ssau.sunsystem.util.mapToUi
import com.ssau.sunsystemlib.core.Constants.DEFAULT_TIMESTEP
import com.ssau.sunsystemlib.core.WorkspaceImpl
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.method.Beeman
import com.ssau.sunsystemlib.util.Vector3d
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainViewModel : ViewModel() {

    private val workspace = WorkspaceImpl( // todo from SettingsFragment
        planets = listOf(
            SpaceBody( // Светило
                mass = 1.989E30,
                coordinate = Vector3d(0.0, 0.0, 0.0),
                velocity = Vector3d(0.0, 0.0, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
                colorId = Color.YELLOW,
            ),
            SpaceBody( // Mercury Development
                mass = 3.285E23,
                coordinate = Vector3d(58E9, 0.0, 0.0),
                velocity = Vector3d(0.0, 48E3, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
                colorId = Color.RED,
            ),
            SpaceBody( // Venus Anus
                mass = 4.867E24,
                coordinate = Vector3d(108E9, 0.0, 0.0),
                velocity = Vector3d(0.0, 35E3, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
                colorId = Color.MAGENTA,
            ),
            SpaceBody( // Terra Internum
                mass = 5.974E24,
                coordinate = Vector3d(150E9, 0.0, 0.0),
                velocity = Vector3d(0.0, 30E3, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
                colorId = Color.BLUE,
            ),
        ),
        scheme = Beeman,
        timeStep = DEFAULT_TIMESTEP,
    ).apply {
        start()
    }

    val uiState: Flow<List<UiSpaceBody>> = workspace.bodiesState.map { systemState ->
        systemState.currentState.bodies.map { it.mapToUi() }
    }

}

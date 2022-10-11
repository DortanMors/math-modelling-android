package com.ssau.sunsystemlib.core

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.method.EulerCramer
import com.ssau.sunsystemlib.util.Vector3d
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WorkspaceImplTest {

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @Test
    fun start() {
        val planets = listOf(
            SpaceBody( // Светило
                mass = 1.989E30,
                coordinate = Vector3d(0.0, 0.0, 0.0),
                velocity = Vector3d(0.0, 0.0, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
            ),
            SpaceBody( // Mercury Development
                mass = 3.285E23,
                coordinate = Vector3d(58E9, 0.0, 0.0),
                velocity = Vector3d(0.0, 48E3, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
            ),
            SpaceBody( // Venus Anus
                mass = 4.867E24,
                coordinate = Vector3d(108E9, 0.0, 0.0),
                velocity = Vector3d(0.0, 35E3, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
            ),
            SpaceBody( // Terra Internum
                mass = 5.974E24,
                coordinate = Vector3d(150E9, 0.0, 0.0),
                velocity = Vector3d(0.0, 30E3, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
            ),
        )
        val workspaceImpl = WorkspaceImpl(
            planets = planets,
            scheme = EulerCramer,
            timeStep = 3600,
        )

        // Start
        coroutineScope.launch {
            workspaceImpl.bodiesState.collect { state ->
                print(state.currentState)
            }
        }
        workspaceImpl.start()
        while (true) {

        }
    }
}
package com.ssau.sunsystemlib.core

import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.method.EulerCramer
import com.ssau.sunsystemlib.util.Vector3d
import kotlinx.coroutines.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class WorkspaceImplTest {

    @Test
    fun start() {
        val planets = listOf(
            SpaceBody( // Светило
                name = "Sun",
                mass = 1.989E30,
                coordinate = Vector3d(0.0, 0.0, 0.0),
                velocity = Vector3d(0.0, 0.0, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
                colorId = 0,
            ),
            SpaceBody( // Mercury Development
                name = "Mercury",
                mass = 3.285E23,
                coordinate = Vector3d(58E9, 0.0, 0.0),
                velocity = Vector3d(0.0, 48E3, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
                colorId = 0,
            ),
            SpaceBody( // Venus Anus
                name = "Venus",
                mass = 4.867E24,
                coordinate = Vector3d(108E9, 0.0, 0.0),
                velocity = Vector3d(0.0, 35E3, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
                colorId = 0,
            ),
            SpaceBody( // Terra Internum
                name = "Earth",
                mass = 5.974E24,
                coordinate = Vector3d(150E9, 0.0, 0.0),
                velocity = Vector3d(0.0, 30E3, 0.0),
                externalForce = Vector3d(0.0, 0.0, 0.0),
                colorId = 0,
            ),
        )
        val workspaceImpl = WorkspaceImpl(
            planets = planets,
        ).apply {
            scheme = EulerCramer
            timeStep = 3600
        }

        // Start
        workspaceImpl.start()
        runBlocking {
            workspaceImpl.bodiesState.collect { state ->
                print(state.currentState)
            }
        }
    }
}
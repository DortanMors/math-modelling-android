package com.ssau.sunsystemlib.core

import com.ssau.sunsystemlib.core.interfaces.PlanetSystem
import com.ssau.sunsystemlib.core.interfaces.Workspace
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.util.Vector3d
import com.ssau.sunsystemlib.util.getGravity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class WorkspaceImpl(
    planets: List<SpaceBody>,
    override val timeStep: Long,
) : Workspace {
    override val planetSystem: PlanetSystem = PlanetSystemImpl(planets)

    private val _bodiesState: MutableStateFlow<PlanetSystem> =
        MutableStateFlow(planetSystem)

    override val bodiesState: Flow<PlanetSystem> = _bodiesState

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    private fun superposition(current: SpaceBody, allBodies: List<SpaceBody>): Vector3d {
        val newForce = Vector3d(0.0, 0.0, 0.0)
        for (planet in allBodies) {
            if (planet != current) {
                newForce += getGravity(
                    mass1 = planet.mass,
                    mass2 = current.mass,
                    radius = planet.coordinate - current.coordinate
                )
            }
        }
        return newForce
    }
}
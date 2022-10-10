package com.ssau.sunsystemlib.core

import com.ssau.sunsystemlib.core.interfaces.PlanetSystem
import com.ssau.sunsystemlib.core.interfaces.Workspace
import com.ssau.sunsystemlib.entity.SpaceBody

class WorkspaceImpl(
        planets: List<SpaceBody>,
        step: Long,
) : Workspace {
    override val planetSystem: PlanetSystem = PlanetSystemImpl(planets)

    override val timeStep: Long = step

    override fun start() {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }
}
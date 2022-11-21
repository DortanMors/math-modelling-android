package com.ssau.sunsystemlib.entity

import com.ssau.sunsystemlib.core.PlanetSystemImpl
import com.ssau.sunsystemlib.core.interfaces.PlanetSystem
import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.util.superposition

data class SystemState(
    val prevState: PlanetSystem,
    val currentState: PlanetSystem = prevState,
    val n: Long = 0
) {

    /**
     * @param scheme - разностная схема для вычисления новых координат и скорости
     * @param deltaTime - разница во времени между новым и текущим состояниями
     * @return новое состояние системы. Текущее состояние станет предыдущим, а новое - текущим.
     */
    fun recalculateState(scheme: Scheme, deltaTime: Long): SystemState {
        val newPlanets = prevState.bodies.zip(currentState.bodies).map { pair ->
            val (prevPlanet, currentPlanet) = pair
            val rawNewPlanet = SpaceBody(
                name = currentPlanet.name,
                mass = currentPlanet.mass,
                coordinate = currentPlanet.coordinate,
                velocity = currentPlanet.velocity,
                externalForce = superposition(currentPlanet, currentState.bodies),
                colorId = currentPlanet.colorId,
            )
            SpaceBody(
                name = rawNewPlanet.name,
                mass = rawNewPlanet.mass,
                coordinate = scheme.calcCoordinate(
                    prevState = prevPlanet,
                    currentState = currentPlanet,
                    newState = rawNewPlanet,
                    deltaTime,
                ),
                velocity = scheme.calcVelocity(
                    prevState = prevPlanet,
                    currentState = currentPlanet,
                    newState = rawNewPlanet,
                    deltaTime
                ),
                externalForce = rawNewPlanet.externalForce,
                colorId = currentPlanet.colorId,
            )
        }
        return SystemState(
            prevState = currentState,
            currentState = PlanetSystemImpl(newPlanets),
            n = n + 1
        )
    }
}

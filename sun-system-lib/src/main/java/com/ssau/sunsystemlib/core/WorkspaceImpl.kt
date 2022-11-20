package com.ssau.sunsystemlib.core

import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.core.interfaces.Workspace
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.entity.SystemState
import com.ssau.sunsystemlib.method.EulerCramer
import com.ssau.sunsystemlib.util.orbitalizeScalar
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class WorkspaceImpl(
    planets: List<SpaceBody>,
) : Workspace {

    override var timeStep: Long = 0

    override var scheme: Scheme = EulerCramer

    override var delay: Long = 0

    /**
     * Внутренний поток состояний системы, разница между двумя последовытельными состояниями = timeStep
     */
    private val _bodiesState: MutableStateFlow<SystemState> =
        MutableStateFlow(
            SystemState(PlanetSystemImpl(planets.orbitalizeScalar()))
                .recalculateState( // для первой итерации предыдущее состояние не учитывается
                    scheme = EulerCramer,
                    deltaTime = timeStep,
                )
        )

    override val bodiesState: Flow<SystemState> = _bodiesState

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun start() {
        coroutineScope.launch {
            bodiesState.collect { currentState ->
                delay(delay)
                val newState = currentState.recalculateState(scheme, timeStep)
                _bodiesState.emit(newState)
            }
        }
    }

    override fun pause() {
        TODO("Not yet implemented")
    }
 }

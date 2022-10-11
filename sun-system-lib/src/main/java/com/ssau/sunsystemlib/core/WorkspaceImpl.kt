package com.ssau.sunsystemlib.core

import com.ssau.sunsystemlib.core.Constants.DEFAULT_DELAY
import com.ssau.sunsystemlib.core.interfaces.Scheme
import com.ssau.sunsystemlib.core.interfaces.Workspace
import com.ssau.sunsystemlib.entity.SpaceBody
import com.ssau.sunsystemlib.entity.SystemState
import com.ssau.sunsystemlib.method.EulerCramer
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class WorkspaceImpl(
    planets: List<SpaceBody>,
    override var scheme: Scheme,
    override val timeStep: Long,
) : Workspace {

    /**
     * Внутренний поток состояний системы, разница между двумя последовытельными состояниями = timeStep
     */
    private val _bodiesState: MutableStateFlow<SystemState> =
        MutableStateFlow(
            SystemState(PlanetSystemImpl(planets))
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
                delay(DEFAULT_DELAY)
                val newState = currentState.recalculateState(scheme, timeStep)
                _bodiesState.emit(newState)
            }
        }
    }

    override fun pause() {
        TODO("Not yet implemented")
    }
 }

package com.ssau.sunsystemlib.core.interfaces

import com.ssau.sunsystemlib.entity.SystemState
import kotlinx.coroutines.flow.Flow

interface Workspace {
    val timeStep: Long
    val bodiesState: Flow<SystemState>
    var scheme: Scheme
    fun start()
    fun pause()
}
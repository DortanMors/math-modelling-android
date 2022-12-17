package com.ssau.sunsystem.util

import android.app.Activity

interface UpdateAppUseCase {
    fun checkForUpdates(activity: Activity, requestCode: Int)
}

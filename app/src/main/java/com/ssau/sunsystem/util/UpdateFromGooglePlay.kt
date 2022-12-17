package com.ssau.sunsystem.util

import android.app.Activity
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.dialog.showAlertDialogAndAwaitResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UpdateFromGooglePlay : UpdateAppUseCase {

    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    override fun checkForUpdates(activity: Activity, requestCode: Int) {
        val appUpdateManager = AppUpdateManagerFactory.create(activity)
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            // If there is an update available, prepare to promote it.
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                appUpdateManager.startUpdateFlowForResult(
                    // Pass the intent that is returned by 'getAppUpdateInfo()'.
                    appUpdateInfo,
                    // Or 'AppUpdateType.IMMEDIATE for immediate updates.
                    if (appUpdateInfo.updatePriority() > 2) AppUpdateType.IMMEDIATE else AppUpdateType.FLEXIBLE,
                    // The current activity.
                    activity,
                    requestCode,
                )
            }

            // If the process of downloading is finished, start the completion flow.
            coroutineScope.launch {
                if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                    val isNeedToUpdate = activity.showAlertDialogAndAwaitResult(
                        title = activity.getString(R.string.time_to_update), //"Update",
                        message = activity.getString(R.string.update_message), //"Update is ready to install",
                        positiveButtonText = activity.getString(R.string.install), //"Install",
                        negativeButtonText = activity.getString(R.string.later), //"Cancel",
                    )
                    if (isNeedToUpdate) {
                        appUpdateManager.completeUpdate()
                    }
                }
            }
        }.addOnFailureListener { e ->
                // Handle the error.
        }
    }
}

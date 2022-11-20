package com.ssau.sunsystem.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.skydoves.colorpickerview.ColorPickerView
import com.ssau.sunsystem.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine


@OptIn(ExperimentalCoroutinesApi::class)
suspend fun AlertDialog.await(buttonText: String) = suspendCancellableCoroutine { cont ->
    setButton(AlertDialog.BUTTON_POSITIVE, buttonText) { _, _ ->
        findViewById<ColorPickerView>(R.id.colorPickerView)?.run {
            cont.resume(color) {
                dismiss()
            }
        }
        dismiss()
    }
    setOnCancelListener { cont.cancel() }
    cont.invokeOnCancellation { dismiss() }
    show()
}

suspend fun Context.showColorPickerDialogAndAwaitResult(): Int =
    MaterialAlertDialogBuilder(this)
        .setTitle(R.string.planet_color)
        .setView(R.layout.fragment_color_picker)
        .create()
        .await(getString(R.string.ok))

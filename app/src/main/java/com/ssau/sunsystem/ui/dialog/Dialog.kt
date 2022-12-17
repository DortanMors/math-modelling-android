package com.ssau.sunsystem.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.skydoves.colorpickerview.ColorPickerView
import com.ssau.sunsystem.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine


@OptIn(ExperimentalCoroutinesApi::class)
suspend fun AlertDialog.awaitColor(buttonText: String) = suspendCancellableCoroutine { cont ->
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

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun AlertDialog.awaitAnswer(
    positiveButtonText: String,
    negativeButtonText: String? = null,
) = suspendCancellableCoroutine { cont ->
    setButton(AlertDialog.BUTTON_POSITIVE, positiveButtonText) { _, _ ->
        cont.resume(true) {
            dismiss()
        }
        dismiss()
    }
    negativeButtonText?.let { text ->
        setButton(AlertDialog.BUTTON_NEGATIVE, text) { _, _ ->
            cont.resume(false) {
                dismiss()
            }
            dismiss()
        }
    }
    setOnCancelListener {
        cont.resume(false) {
            dismiss()
        }
    }
    cont.invokeOnCancellation { dismiss() }
    show()
}

suspend fun Context.showColorPickerDialogAndAwaitResult(): Int =
    MaterialAlertDialogBuilder(this)
        .setTitle(R.string.planet_color)
        .setView(R.layout.fragment_color_picker)
        .create()
        .awaitColor(getString(R.string.ok))

suspend fun Context.showAlertDialogAndAwaitResult(
    title: String,
    message: String,
    positiveButtonText: String,
    negativeButtonText: String? = null,
): Boolean =
    MaterialAlertDialogBuilder(this)
        .setTitle(title)
        .setMessage(message)
        .create()
        .awaitAnswer(positiveButtonText, negativeButtonText)

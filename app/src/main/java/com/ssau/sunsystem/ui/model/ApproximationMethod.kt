package com.ssau.sunsystem.ui.model

import androidx.annotation.StringRes
import com.ssau.sunsystem.R

enum class ApproximationMethod(@StringRes val methodNameId: Int) {
    EULER(R.string.euler),
    EULER_CRAMER(R.string.euler_cramer),
    BEEMAN(R.string.beeman),
    VERLET(R.string.verlet),
}
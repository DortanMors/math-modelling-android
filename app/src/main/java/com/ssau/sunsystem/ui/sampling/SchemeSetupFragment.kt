package com.ssau.sunsystem.ui.sampling

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.screen.planetsystem.PlanetSystemFragment
import com.ssau.sunsystem.ui.viewmodel.MainViewModel
import com.ssau.sunsystem.util.tag
import java.lang.NumberFormatException

class SchemeSetupFragment : Fragment(R.layout.fragment_scheme_setup) {

    private val viewModel by activityViewModels<MainViewModel>()

    private val timeStepEditText: TextInputEditText?
        get() = view?.findViewById(R.id.time_step)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timeStepEditText?.setText(viewModel.timeStep.toString())
        view.findViewById<Button>(R.id.next).setOnClickListener {
            try {
                viewModel.timeStep = timeStepEditText?.text.toString().toLong()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, PlanetSystemFragment.newInstance())
                    .addToBackStack(PlanetSystemFragment.tag)
                    .commit()
            } catch (e: NumberFormatException) {
                timeStepEditText?.error = getString(R.string.must_be_number)
            }
        }
    }

    companion object {
        fun newInstance() = SchemeSetupFragment()
    }
}
package com.ssau.sunsystem.ui.screen.planetsystem

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.switchmaterial.SwitchMaterial
import com.ssau.sunsystem.Defaults
import com.ssau.sunsystem.Defaults.METERS_PER_PIXEL
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.dialog.shortToast
import com.ssau.sunsystem.ui.view.PlanetSystemView
import com.ssau.sunsystem.ui.viewmodel.MainViewModel
import com.ssau.sunsystem.util.mapToUi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PlanetSystemFragment : Fragment(R.layout.fragment_planet_system) {

    private val viewModel by activityViewModels<MainViewModel>()

    private lateinit var startPauseToggle: ToggleButton

    private lateinit var yearsView: TextView

    private lateinit var settingsLayout: View

    private lateinit var zoomSettings: View

    override fun onStart() {
        super.onStart()
        activity?.setTitle(viewModel.schemeNameId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val planetSystemView = view.findViewById<PlanetSystemView>(R.id.planet_system_view)
        yearsView = view.findViewById(R.id.years_view)
        startPauseToggle = view.findViewById(R.id.start_pause)
        settingsLayout = view.findViewById(R.id.settings_layout)
        view.findViewById<View>(R.id.settings).setOnClickListener {
            settingsLayout.isVisible = !settingsLayout.isVisible
        }
        view.findViewById<SwitchMaterial>(R.id.switch_show_names).run {
            isChecked = planetSystemView.showNames
            setOnCheckedChangeListener { _, isChecked ->
                planetSystemView.showNames = isChecked
            }
        }
        view.findViewById<SwitchMaterial>(R.id.switch_show_physics).run {
            isChecked = planetSystemView.showPhysics
            setOnCheckedChangeListener { _, isChecked ->
                planetSystemView.showPhysics = isChecked
            }
        }
        view.findViewById<SwitchMaterial>(R.id.switch_show_paths).run {
            isChecked = planetSystemView.showPaths
            setOnCheckedChangeListener { _, isChecked ->
                planetSystemView.showPaths = isChecked
            }
        }
        zoomSettings = view.findViewById(R.id.scale_layout)
        view.findViewById<SwitchMaterial>(R.id.switch_show_zoom).run {
            isChecked = true
            setOnCheckedChangeListener { _, isChecked ->
                zoomSettings.isVisible = isChecked
            }
        }
        view.findViewById<View>(R.id.fast_backward).setOnClickListener {
            viewModel.fastBackward()
            showDaysPerSecond()
        }
        view.findViewById<View>(R.id.fast_forward).setOnClickListener {
            viewModel.fastForward()
            showDaysPerSecond()
        }
        view.findViewById<View>(R.id.zoom_in).setOnClickListener {
            val oldZoom = METERS_PER_PIXEL
            METERS_PER_PIXEL -= Defaults.SCALE_STEP
            planetSystemView.scalePaths(oldZoom / METERS_PER_PIXEL)
        }
        view.findViewById<View>(R.id.zoom_out).setOnClickListener {
            val oldZoom = METERS_PER_PIXEL
            METERS_PER_PIXEL += Defaults.SCALE_STEP
            planetSystemView.scalePaths(oldZoom / METERS_PER_PIXEL)
        }
        startPauseToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.start()
                showDaysPerSecond()
            } else {
                viewModel.pause()
            }
        }
        lifecycleScope.launch {
            viewModel.uiState.first().let { planets ->
                planetSystemView.post {
                    planetSystemView.setInitialPlanetsCoordinates(
                        planets.map { it.mapToUi(requireContext()) }
                    )
                }
            }
            viewModel.yearsPassed.collect {
                yearsView.text = getString(R.string.years_passed, it)
            }
        }
        planetSystemView.post {
            lifecycleScope.launch {
                viewModel.uiState.collect { planets ->
                    planetSystemView.setSystemState(planets.map { it.mapToUi(requireContext()) })
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        view?.post {
            if (startPauseToggle.isChecked) {
                viewModel.start()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.pause()
    }

    private fun showDaysPerSecond() =
        context?.shortToast(text = getString(R.string.days_per_second, viewModel.daysPerSecond))

    companion object {
        fun newInstance() = PlanetSystemFragment()
    }
}

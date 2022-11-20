package com.ssau.sunsystem.ui.screen.planetsystem

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.view.PlanetSystemView
import com.ssau.sunsystem.ui.viewmodel.MainViewModel
import com.ssau.sunsystem.util.mapToUi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PlanetSystemFragment : Fragment(R.layout.fragment_planet_system) {

    companion object {
        fun newInstance() = PlanetSystemFragment()
    }

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val planetSystemView = view.findViewById<PlanetSystemView>(R.id.planet_system_view)
        lifecycleScope.launch {
            viewModel.uiState.first().let { planets ->
                planetSystemView.post {
                    planetSystemView.setInitialPlanetsCoordinates(
                        planets.map { it.mapToUi(requireContext()) }
                    )
                }
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
}

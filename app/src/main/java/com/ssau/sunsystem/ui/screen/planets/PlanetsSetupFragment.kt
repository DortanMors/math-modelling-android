package com.ssau.sunsystem.ui.screen.planets

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.CheckBox
import android.widget.CheckedTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.ssau.sunsystem.Defaults
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.dialog.showColorPickerDialogAndAwaitResult
import com.ssau.sunsystem.ui.screen.prepare.PrepareFragment
import com.ssau.sunsystem.ui.viewmodel.MainViewModel
import com.ssau.sunsystem.util.tag
import com.ssau.sunsystemlib.util.Vector3d
import kotlinx.coroutines.launch

class PlanetsSetupFragment : Fragment(R.layout.fragment_planets) {

    private val viewModel by activityViewModels<MainViewModel>()

    private lateinit var adapter: PlanetsAdapter

    override fun onStart() {
        super.onStart()
        activity?.setTitle(R.string.planets_setup)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PlanetsAdapter(
            planets = viewModel.customizedPlanets.takeIf { it.isNotEmpty() }
                ?: Defaults.getPlanetsUi(requireContext())
        ) { planets ->
            viewModel.customizedPlanets = planets
        }
        adapter.setOnColorPickClick { position ->
            lifecycleScope.launch {
                context?.let { context ->
                    val selectedColor = context.showColorPickerDialogAndAwaitResult()
                    adapter.setPlanetColor(position, selectedColor)
                }
            }
        }
        adapter.setOnAutoVelocityCallback { position, motherPlanet, planet ->
            adapter.setPlanetVelocity(position, Vector3d(0.0, viewModel.calculateVelocity(motherPlanet, planet), 0.0))
        }

        val planetsRecycler = view.findViewById<RecyclerView>(R.id.rv_planets)
        planetsRecycler.adapter = adapter
        view.findViewById<View>(R.id.next).setOnClickListener {
            viewModel.customizedPlanets = (planetsRecycler.adapter as PlanetsAdapter).getPlanets()
            viewModel.setupPlanets()
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, PrepareFragment.newInstance())
                .addToBackStack(PrepareFragment.tag)
                .commit()
        }

        view.findViewById<View>(R.id.add).setOnClickListener {
            val lastPlanet: CustomizedPlanet? = adapter.getPlanets().lastOrNull()
            adapter.addPlanet(viewModel.createPlanet(lastPlanet, getString(R.string.planet_name)))
        }

        view.findViewById<View>(R.id.remove).setOnClickListener {
            adapter.deletePlanet(adapter.getPlanets().size - 1)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_planets, menu)
        (menu.findItem(R.id.toggle_xyz_coordinates).actionView as CheckBox).run {
            isChecked = Defaults.isXyzCoordinates
            setOnClickListener { checkView, ->
                adapter.setXyzVisible((checkView as CheckBox).isChecked)
            }
            super.onCreateOptionsMenu(menu, inflater)
        }
    }

    companion object {
        fun newInstance() = PlanetsSetupFragment()
    }
}
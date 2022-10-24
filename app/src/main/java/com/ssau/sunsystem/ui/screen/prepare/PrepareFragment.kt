package com.ssau.sunsystem.ui.screen.prepare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.screen.planetsystem.PlanetSystemFragment
import com.ssau.sunsystem.ui.viewmodel.MainViewModel

class PrepareFragment : Fragment() {

    companion object {
        fun newInstance() = PrepareFragment()
    }

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_prepare, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.methods).apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = ApproximationAdapter(context) { method ->
                viewModel.setScheme(method)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, PlanetSystemFragment.newInstance())
                    .commitNow()
            }
        }
    }
}
package com.ssau.sunsystem.ui.screen.prepare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.sampling.SchemeSetupFragment
import com.ssau.sunsystem.ui.viewmodel.MainViewModel
import com.ssau.sunsystem.util.tag

class PrepareFragment : Fragment(R.layout.fragment_prepare) {

    companion object {
        fun newInstance() = PrepareFragment()
    }

    private val viewModel by activityViewModels<MainViewModel>()

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
                    .replace(R.id.container, SchemeSetupFragment.newInstance())
                    .addToBackStack(SchemeSetupFragment.tag)
                    .commit()
            }
        }
    }
}
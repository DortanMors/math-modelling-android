package com.ssau.sunsystem.ui.screen.prepare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.model.ApproximationMethod
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
        with(view.findViewById<Spinner>(R.id.approximation_dropdown)) {
            adapter = ArrayAdapter(
                requireContext(),
                // Разметка выбранного элемента
                android.R.layout.simple_spinner_item,
                ApproximationMethod.values().map { method -> getString(method.methodNameId) },
            ).apply {
                // Разметка для элемента выпадающего списка
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            setSelection(viewModel.method.ordinal)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.method = ApproximationMethod.values()[position]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }

    }
}
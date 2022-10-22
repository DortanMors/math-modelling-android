package com.ssau.sunsystem.ui.screen.prepare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.viewmodel.MainViewModel

class PrepareFragment : Fragment() {

    companion object {
        fun newInstance() = PrepareFragment()
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_prepare, container, false)
    }


}
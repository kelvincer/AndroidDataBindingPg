package com.keliceru.challengeprosegur.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.keliceru.challengeprosegur.R
import com.keliceru.challengeprosegur.databinding.FragmentRegisterBinding
import com.keliceru.challengeprosegur.presentation.viewmodels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val viewModel: RegisterViewModel by viewModels()
    lateinit var binding: FragmentRegisterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        viewModel.getCinemaRooms()

    }
}
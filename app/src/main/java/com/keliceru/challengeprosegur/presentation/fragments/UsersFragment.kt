package com.keliceru.challengeprosegur.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.keliceru.challengeprosegur.R
import com.keliceru.challengeprosegur.databinding.FragmentRegisteredUsersBinding
import com.keliceru.challengeprosegur.presentation.viewmodels.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_registered_users) {

    private val viewModel: UsersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentRegisteredUsersBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

            }
        }

        viewModel.getInformationFromDB()
    }
}
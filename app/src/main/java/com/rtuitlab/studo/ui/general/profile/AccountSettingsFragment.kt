package com.rtuitlab.studo.ui.general.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialSharedAxis
import com.rtuitlab.studo.R
import com.rtuitlab.studo.currentUser
import com.rtuitlab.studo.databinding.FragmentAccountSettingsBinding
import com.rtuitlab.studo.server.Status
import com.rtuitlab.studo.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_account_settings.*
import kotlinx.android.synthetic.main.view_collapsing_toolbar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AccountSettingsFragment: Fragment() {

    private val viewModel: ProfileViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis.create(requireContext(), MaterialSharedAxis.Z,true)
        exitTransition = MaterialSharedAxis.create(requireContext(), MaterialSharedAxis.Z,false)
        sharedElementEnterTransition = MaterialContainerTransform(requireContext()).apply {
            scrimColor = ContextCompat.getColor(requireContext(), android.R.color.transparent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAccountSettingsBinding>(
            inflater,
            R.layout.
            fragment_account_settings,
            container,
            false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collapsingToolbar.title = getString(R.string.account)
        setListeners()
        fillUserData()
        viewModel.currentUserResource.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    swipeContainer.isRefreshing = false
                    fillUserData()
                }
                Status.ERROR -> {
                    swipeContainer.isRefreshing = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    swipeContainer.isRefreshing = true
                }
            }
        })
    }

    private fun setListeners() {
        swipeContainer.setOnRefreshListener { viewModel.updateCurrentUser() }
    }

    private fun fillUserData() {
        avatarView.text = viewModel.userInitials
        nameInput.editText!!.setText(currentUser!!.name)
        surnameInput.editText!!.setText(currentUser!!.surname)
        cardNumberInput.editText!!.setText(currentUser!!.studentCardNumber)
    }
}
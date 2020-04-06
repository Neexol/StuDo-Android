package com.rtuitlab.studo.ui.auth.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.rtuitlab.studo.R
import com.rtuitlab.studo.databinding.FragmentLoginBinding
import com.rtuitlab.studo.server.Status
import com.rtuitlab.studo.ui.general.MainActivity
import com.rtuitlab.studo.viewmodels.AuthViewModel
import kotlinx.android.synthetic.main.dialog_reset_password.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

    private val viewModel: AuthViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.
            fragment_login,
            container,
            false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearErrors()
        loginBtn.dispose()
    }

    private fun setListeners() {
        registerLink.setOnClickListener {
            viewModel.clearErrors()
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        resetPasswordBtn.setOnClickListener {
            viewModel.clearResetEmail()
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordDialog)
        }
    }

    private fun setObservers() {
        viewModel.loginResource.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    loginBtn.revertAnimation()
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                    requireActivity().finish()
                }
                Status.ERROR -> {
                    loginBtn.revertAnimation()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> loginBtn.startAnimation()
            }
        })

        viewModel.resetResource.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Status.SUCCESS -> {
                    loginBtn.revertAnimation()
                    Snackbar.make(requireView(), getString(R.string.check_email_to_reset), Snackbar.LENGTH_LONG).show()
                }
                Status.ERROR -> {
                    loginBtn.revertAnimation()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> loginBtn.startAnimation()
            }
        })
    }
}

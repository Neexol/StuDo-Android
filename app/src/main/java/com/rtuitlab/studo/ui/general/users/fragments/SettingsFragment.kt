package com.rtuitlab.studo.ui.general.users.fragments

import android.os.Bundle
import android.view.View
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.rtuitlab.studo.R
import kotlinx.android.synthetic.main.view_collapsing_toolbar.*

class SettingsFragment: PreferenceFragmentCompat() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collapsingToolbar.title = getString(R.string.settings)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey)
        setListeners()
    }

    private fun setListeners() {
        findPreference<SwitchPreferenceCompat>("themeSwitch")
            ?.setOnPreferenceChangeListener { _, _ ->
                requireActivity().recreate()
                true
            }


        findPreference<ListPreference>("languagesDropdown")
            ?.setOnPreferenceChangeListener { _, _ ->
                requireActivity().recreate()
                true
            }
    }
}
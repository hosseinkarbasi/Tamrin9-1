package com.example.informationpagewithfragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.informationpagewithfragments.databinding.SaveInformationFragmentBinding
import com.google.android.material.snackbar.Snackbar

class SaveInfoFragment : Fragment(R.layout.save_information_fragment) {
    private lateinit var binding: SaveInformationFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SaveInformationFragmentBinding.bind(view)

        var shPref: SharedPreferences =
            context!!.getSharedPreferences("kotlinsharedpreference", Context.MODE_PRIVATE)

        val args = this.arguments
        with(binding) {

            val fullName = args?.get("fullName").toString()
            val userName = args?.get("userName").toString()
            val email = args?.get("email").toString()
            val password = args?.get("password").toString()
            val txtGender = args?.get("gender").toString()

            tvFullName.text = fullName
            tvUserName.text = userName
            tvEmail.text = email
            tvPassword.text = password
            tvGender.text = txtGender

            btnSaveInfo.setOnClickListener {
                val editor = shPref.edit()
                editor.putString("fullName_Key", fullName)
                editor.putString("userName_Key", userName)
                editor.putString("email_Key", email)
                editor.putString("password_Key", password)
                editor.putString("gender_Key", txtGender)
                editor.apply()
                editor.commit()

                Snackbar.make(it, "${tvUserName.text} Your Information Saved", Snackbar.LENGTH_LONG)
                    .show()

                val fragment = RegisterFragment()
                parentFragmentManager.beginTransaction().replace(R.id.nav_container, fragment)
                    .commit()
            }
        }
    }
}
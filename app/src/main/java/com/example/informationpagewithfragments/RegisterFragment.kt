package com.example.informationpagewithfragments

import android.os.Bundle
import android.text.Editable
import android.util.Patterns
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.informationpagewithfragments.databinding.RegisterFragmentBinding
import java.util.regex.Pattern
import android.text.TextWatcher


class RegisterFragment : Fragment(R.layout.register_fragment) {
    private lateinit var binding: RegisterFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RegisterFragmentBinding.bind(view)

        with(binding) {
            btnRegister.setOnClickListener {

                val txtGender = if (Male.isChecked) {
                    "Male"
                } else "Female"

                val bundle = Bundle()
                bundle.putString("fullName", edFullName.text.toString())
                bundle.putString("userName", edUserName.text.toString())
                bundle.putString("email", edEmali.text.toString())
                bundle.putString("gender", txtGender)
                bundle.putString("password", edPassword.text.toString())

                if (!validateEmail() || !validatePassword() || !validateRePassword()) {
                    return@setOnClickListener
                } else {
                    val fragment = SaveInfoFragment()
                    fragment.arguments = bundle
                    parentFragmentManager.beginTransaction().replace(R.id.nav_container, fragment)
                        .commit()
                }
            }
        }
    }


    private fun validatePassword(): Boolean {
        val passwordPattern: Pattern = Pattern.compile(
            "^" +
                    "(?=.*[@#$%^&+=])" +  // at least 1 special character
                    "(?=\\S+$)" +  // no white spaces
                    ".{4,}" +  // at least 4 characters
                    "$"
        )
        return if (binding.edPassword.text!!.isEmpty()) {
            binding.textInputPassword.error = "Field can not be empty"
            false
        } else if (!passwordPattern.matcher(binding.edPassword.text.toString()).matches()) {
            binding.textInputPassword.error = "Password is too weak"
            false
        } else {
            binding.textInputPassword.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        return if (binding.edEmali.text!!.isEmpty()) {
            binding.textInputEmail.error = "Field can not be empty"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.edEmali.text.toString()).matches()) {
            binding.textInputEmail.error = "Please enter a valid email address"
            false
        } else {
            binding.textInputEmail.error = null
            true
        }
    }

    private fun validateRePassword(): Boolean {
        return if (binding.edPassword.text.toString() == binding.edRePassword.text.toString())
            true
        else {
            binding.textInputRepassword.error = "Confirm password is not correct"
            false
        }
    }
}





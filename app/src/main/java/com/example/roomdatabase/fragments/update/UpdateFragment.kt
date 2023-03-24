package com.example.roomdatabase.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.FragmentUpdateBinding
import com.example.roomdatabase.model.User
import com.example.roomdatabase.viewmodel.UserViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var bind: FragmentUpdateBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentUpdateBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        bind.updateFirstNameEt.setText(args.currentUser.firsName)
        bind.updateLastNameEt.setText(args.currentUser.lastName)
        bind.updateAgeEt.setText(args.currentUser.age.toString())

        bind.updateBtn.setOnClickListener{
            updateItem()
        }

        return bind.root    //inflater.inflate(R.layout.fragment_update, container, false)
    }

    private fun updateItem() {
        val fName = bind.updateFirstNameEt.text.toString()
        val lName = bind.updateLastNameEt.text.toString()
        val age = Integer.parseInt(bind.updateAgeEt.text.toString())

        if (inputCheck(fName, lName, bind.updateAgeEt.text)){
            val user = User(args.currentUser.id, fName, lName, age)
            mUserViewModel.updateUser(user)

            Toast.makeText(requireContext(), "Запись обновлена успешно", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()
    }

}
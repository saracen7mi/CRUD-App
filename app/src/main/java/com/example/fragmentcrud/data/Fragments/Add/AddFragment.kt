package com.example.fragmentcrud.data.Fragments.Add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.fragmentcrud.R
import com.example.fragmentcrud.data.Fragments.model.User
import com.example.fragmentcrud.data.Fragments.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlin.random.Random


class AddFragment : Fragment() {

    lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        view.addBotom.setOnClickListener {
            addDataToDataBase()
        }
        return view
    }

    private fun addDataToDataBase() {

        val name = name_editText.text.toString()
        val surname = surname_editText.text.toString()
        if (inputChack(name, surname)) {
            val users = User(Random.nextInt(), name, surname)
            userViewModel.addUser(users)

            Toast.makeText(requireContext(), "add Succesful", Toast.LENGTH_SHORT).show()
        }
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

    fun inputChack(name: String, suranme: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(suranme))

    }
}

/*
*
* Sql database, commandas
*
*
* */

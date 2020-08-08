package com.example.fragmentcrud.data.Fragments.update

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.view.View.inflate
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Update
import com.example.fragmentcrud.R


import com.example.fragmentcrud.data.Fragments.model.User
import com.example.fragmentcrud.data.Fragments.repository.UserRepository
import com.example.fragmentcrud.data.Fragments.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import kotlinx.android.synthetic.main.fragment_update.view.update_name_editText
import kotlinx.android.synthetic.main.fragment_update.view.update_surname_editText
import java.util.zip.Inflater


class Update : Fragment() {

    private val args: UpdateArgs by navArgs()
    lateinit var repository: UserRepository
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_name_editText.setText(args.curentuser.name.toString())
        view.update_surname_editText.setText(args.curentuser.surname.toString())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.updateBotom.setOnClickListener {
            UpdateItem()
        }
        setHasOptionsMenu(true)


    }

    fun UpdateItem() {
        val name = update_name_editText.text.toString()
        val surname = update_surname_editText.text.toString()

        if (inputChack(name, surname)) {
            val updateUser = User(args.curentuser.id, name, surname)
            userViewModel.repos.updateuser(updateUser)

        }
        findNavController().navigate(R.id.action_update_to_listFragment2)
    }

    fun inputChack(name: String, suranme: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(suranme))

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu)
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete) {

            deleteUser()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            userViewModel.deleteuser(args.curentuser)
            Toast.makeText(requireContext(),"Succesful delete${args.curentuser.name}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_update_to_listFragment2)
        }


        builder.setNegativeButton("NO") { _, _vi -> }
        builder.setTitle("DELTE ${args.curentuser.name}?")
        builder.setTitle("are you shure you want to the delete${args.curentuser.name}")
        builder.create().show()
    }

}


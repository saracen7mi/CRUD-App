package com.example.fragmentcrud.data.Fragments.List

import android.app.AlertDialog
import android.app.Application
import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentcrud.R
import com.example.fragmentcrud.data.Fragments.model.User
import com.example.fragmentcrud.data.Fragments.viewmodel.UserViewModel
import com.example.fragmentcrud.data.Fragments.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_list.view.recyclerView


class ListFragment : Fragment() {
    var userAdapter: UserAdapter? = null
    lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        userAdapter = UserAdapter()
        view.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        view.recyclerView.adapter = userAdapter



        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getAllUsers.observe(viewLifecycleOwner, Observer { userList ->
            userAdapter!!.setData(userList)


        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.floatingActionButton.setOnClickListener {

            findNavController().navigate(R.id.action_listFragment_to_addFragment)


        }
        setHasOptionsMenu(true)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu)
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete) {

            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            userViewModel.repos.deleteAll(user = User(1, "MIRZA", "SARACEVIC"))
            Toast.makeText(requireContext(), "Succesful delete allUser", Toast.LENGTH_SHORT).show()

        }


        builder.setNegativeButton("NO") { _, _vi -> }
        builder.setTitle("DELTE All User")
        builder.setTitle("are you shure you want to the delete all User")
        builder.create().show()
    }

}


package com.example.fragmentcrud.data.Fragments.List


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController

import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentcrud.R
import com.example.fragmentcrud.data.Fragments.model.User
import kotlinx.android.synthetic.main.users_list_item.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    var userList = emptyList<User>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var names = view.name_text_View
        var surnames = view.surname_text_View
        val ids = view.id_text_View
    }

    override fun getItemCount(): Int {

        return if (userList == null) 0 else userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curentList = userList[position ]
        holder.names.text = curentList.name
        holder.surnames.text = curentList.surname
        holder.ids.text = curentList.id.toString()

        holder.itemView.rowLeyout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdate(curentList)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.users_list_item, parent, false)
        return ViewHolder(view)
    }
}
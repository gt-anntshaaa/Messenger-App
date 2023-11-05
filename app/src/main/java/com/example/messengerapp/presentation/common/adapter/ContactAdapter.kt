package com.example.messengerapp.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.messengerapp.data.model.users.Contact
import com.example.messengerapp.databinding.ItemContactBinding
import com.squareup.picasso.Picasso

class ContactAdapter (private val eventCallback : (Contact) -> Unit)
    : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    private val listContact: MutableList<Contact> = ArrayList()

        inner class ContactViewHolder(private val binding: ItemContactBinding) :
            RecyclerView.ViewHolder(binding.root){
                fun bind(contact : Contact){
                    binding.name.text = contact.name
                    binding.email.text = contact.email
                    //Picasso.get().load(contact.images).into(binding.imgProfile)

                    binding.root.setOnClickListener { eventCallback(contact) }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ItemContactBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun getItemCount(): Int {
        return listContact.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(listContact[position])
    }

    fun submit(newContact : List<Contact>){
        listContact.apply {
            //clear()
            addAll(newContact)
            notifyDataSetChanged()
        }
    }
}
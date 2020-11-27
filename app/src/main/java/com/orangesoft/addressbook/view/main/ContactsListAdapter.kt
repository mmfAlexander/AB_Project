package com.orangesoft.addressbook.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orangesoft.addressbook.R
import com.orangesoft.addressbook.model.AggregatedContact

class ContactsListAdapter(private val onClick: (AggregatedContact) -> Unit) :
    ListAdapter<AggregatedContact, ContactViewHolder>(ContactDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact)
    }

}

class ContactViewHolder(itemView: View, val onClick: (AggregatedContact) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val avatarView: ImageView = itemView.findViewById(R.id.avatar)
    private val nameView: TextView = itemView.findViewById(R.id.name)
    private val phoneView: TextView = itemView.findViewById(R.id.main_phone)

    fun bind(contact: AggregatedContact) {
        nameView.text = contact.name
        phoneView.text = contact.mainPhone
        Glide.with(itemView).clear(avatarView)
        Glide.with(itemView)
            .load(contact.photoUri)
            .placeholder(R.drawable.ic_baseline_person_24)
            .into(avatarView)
        itemView.setOnClickListener { onClick(contact) }
    }

}

object ContactDiffCallback : DiffUtil.ItemCallback<AggregatedContact>() {

    override fun areItemsTheSame(oldItem: AggregatedContact, newItem: AggregatedContact): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: AggregatedContact,
        newItem: AggregatedContact
    ): Boolean {
        return oldItem.name == newItem.name && oldItem.mainPhone == newItem.mainPhone
    }

}
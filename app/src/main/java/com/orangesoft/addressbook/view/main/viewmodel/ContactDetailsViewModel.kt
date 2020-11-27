package com.orangesoft.addressbook.view.main.viewmodel

import androidx.databinding.ObservableField
import com.orangesoft.addressbook.model.AggregatedContact
import com.orangesoft.addressbook.view.base.viewmodel.BaseViewModel

class ContactDetailsViewModel : BaseViewModel() {

    val name = ObservableField<String>()
    val phone = ObservableField<String>()
    val photoUri = ObservableField<String>()

    fun initContact(contact: AggregatedContact) {
        name.set(contact.name)
        phone.set(contact.mainPhone)
        photoUri.set(contact.photoUri)
    }

}
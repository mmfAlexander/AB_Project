package com.orangesoft.addressbook.view.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.orangesoft.addressbook.domain.SystemContactsProvider
import com.orangesoft.addressbook.model.AggregatedContact
import com.orangesoft.addressbook.model.Phone
import com.orangesoft.addressbook.view.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsListViewModel(private val contactsProvider: SystemContactsProvider) :
    BaseViewModel() {

    val contacts: LiveData<ArrayList<AggregatedContact>> =
        liveData(context = viewModelScope.coroutineContext) {
            val data = collectContacts()
            emit(data)
        }

    private suspend fun collectContacts(): ArrayList<AggregatedContact> =
        withContext(Dispatchers.IO) {
            val data = contactsProvider.collectContacts()
            val groupedContacts = data.groupBy { contact -> contact.id }

            val aggregatedContacts = ArrayList<AggregatedContact>()

            groupedContacts.forEach { entry ->
                val contacts = entry.value
                val phones = contacts.map { contact -> Phone(contact.phone, contact.phoneType) }
                aggregatedContacts.add(
                    AggregatedContact(
                        contacts[0].name,
                        contacts[0].phone,
                        phones,
                        contacts[0].photoUri
                    )
                )
            }
            aggregatedContacts
        }
}


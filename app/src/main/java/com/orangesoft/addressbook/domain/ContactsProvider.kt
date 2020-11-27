package com.orangesoft.addressbook.domain

import com.orangesoft.addressbook.model.Contact

interface ContactsProvider {
    fun collectContacts(): List<Contact>
}
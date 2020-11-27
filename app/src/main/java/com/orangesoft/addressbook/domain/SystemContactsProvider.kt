package com.orangesoft.addressbook.domain

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import androidx.annotation.WorkerThread
import com.orangesoft.addressbook.model.Contact

class SystemContactsProvider(private val context: Context) : ContactsProvider {

    @WorkerThread
    override fun collectContacts(): List<Contact> {
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.PHOTO_URI,
            ContactsContract.CommonDataKinds.Phone.TYPE
        )
        val cursor: Cursor? = context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone._ID
        )
        if (cursor == null || cursor.count == 0) {
            return emptyList()
        }
        val contacts = ArrayList<Contact>(cursor.count)
        val contactIdIndex =
            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
        val displayNameIndex =
            cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
        val phoneNumberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
        val photoUriIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI)
        val phoneType = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)

        cursor.use {
            while (it.moveToNext()) {
                val contactId = cursor.getLong(contactIdIndex)
                val displayName = cursor.getString(displayNameIndex)
                val phoneNumber = cursor.getString(phoneNumberIndex)
                val photoUri = cursor.getString(photoUriIndex)
                if (phoneNumber.isNullOrBlank()) {
                    continue
                }
                val contact = Contact(contactId, displayName, phoneNumber, phoneType, photoUri)
                contacts.add(contact)
            }
        }
        return contacts
    }

}
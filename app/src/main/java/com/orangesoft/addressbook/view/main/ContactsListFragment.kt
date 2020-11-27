package com.orangesoft.addressbook.view.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.orangesoft.addressbook.R
import com.orangesoft.addressbook.model.AggregatedContact
import com.orangesoft.addressbook.view.base.BaseFragment
import com.orangesoft.addressbook.view.extensions.setVisible
import com.orangesoft.addressbook.view.main.ContactDetailsFragment.Companion.CONTACT_KEY
import com.orangesoft.addressbook.view.main.viewmodel.ContactsListViewModel
import kotlinx.android.synthetic.main.fragment_contacts_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class ContactsListFragment : BaseFragment() {

    private val viewModel: ContactsListViewModel by viewModel()
    private val contactsAdapter = ContactsListAdapter { aggregatedContact -> openContact(aggregatedContact) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_contacts_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsList.layoutManager = LinearLayoutManager(context)
        contactsList.adapter = contactsAdapter
        requestContacts()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CONTACTS_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                initContacts()
            } else {
                val view = view ?: return
                Snackbar.make(view, R.string.permission_not_granted, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestContacts() {
        val permissionCheck = checkSelfPermission(this.requireContext(), Manifest.permission.READ_CONTACTS)
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), CONTACTS_REQUEST_CODE)
        } else {
            initContacts()
        }
    }

    private fun initContacts() {
        viewModel.contacts.observe(
                this.viewLifecycleOwner,
                Observer<ArrayList<AggregatedContact>> { contacts ->
                    contactsAdapter.submitList(contacts)
                    emptyView.setVisible(contacts.isEmpty())
                    contactsList.setVisible(contacts.isNotEmpty())
                })
    }

    private fun openContact(contact: AggregatedContact) {
        val bundle = Bundle()
        bundle.putParcelable(CONTACT_KEY, contact)
        findNavController().navigate(
                R.id.action_contactsListFragment_to_contactDetailsFragment,
                bundle
        )
    }

    companion object {

        const val CONTACTS_REQUEST_CODE = 1
    }

}

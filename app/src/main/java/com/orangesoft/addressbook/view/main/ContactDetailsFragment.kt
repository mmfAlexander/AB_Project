package com.orangesoft.addressbook.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.orangesoft.addressbook.R
import com.orangesoft.addressbook.databinding.FragmentContactDetailsBinding
import com.orangesoft.addressbook.model.AggregatedContact
import com.orangesoft.addressbook.view.base.BaseFragment
import com.orangesoft.addressbook.view.main.viewmodel.ContactDetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ContactDetailsFragment : BaseFragment() {

    private val viewModel: ContactDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contact: AggregatedContact? = arguments?.getParcelable(CONTACT_KEY) as AggregatedContact?
        if (contact != null) {
            viewModel.initContact(contact)
        } else {
            throw RuntimeException("opened ContactDetailsFragment with null contact")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentContactDetailsBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_contact_details,
                container,
                false
        )
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {

        const val CONTACT_KEY = "contact_key"

    }
}

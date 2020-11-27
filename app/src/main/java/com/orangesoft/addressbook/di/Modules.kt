package com.orangesoft.addressbook.di

import com.orangesoft.addressbook.domain.SystemContactsProvider
import com.orangesoft.addressbook.view.main.viewmodel.ContactDetailsViewModel
import com.orangesoft.addressbook.view.main.viewmodel.ContactsListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val applicationModule = module {
    factory { SystemContactsProvider(androidContext()) }
}

private val viewModels = module {
    viewModel { ContactsListViewModel(get()) }
    viewModel { ContactDetailsViewModel() }
}

val appModules = listOf(applicationModule, viewModels)
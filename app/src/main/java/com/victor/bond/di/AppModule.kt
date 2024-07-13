package com.victor.bond.di

import com.victor.bond.BondApp
import com.victor.bond.connect.ConnectViewModel
import com.victor.bond.video.VideoCallViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    factory {
        val app = androidContext().applicationContext as BondApp
        app.client
    }

    viewModelOf(::ConnectViewModel)
    viewModelOf(::VideoCallViewModel)
}
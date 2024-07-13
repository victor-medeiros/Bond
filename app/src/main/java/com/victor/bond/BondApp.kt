package com.victor.bond

import android.app.Application
import com.victor.bond.di.appModule
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import io.getstream.video.android.model.UserType
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.UUID

class BondApp: Application() {

    private var currentName: String? = null
    var client: StreamVideo? = null
        private set

    fun initVideoClient(username: String) {
        currentName = username
        StreamVideo.removeClient()
        client = StreamVideoBuilder(
            context = this,
            apiKey = BuildConfig.STREAM_API_KEY,
            user = User(
                id = username + UUID.randomUUID(),
                name = username,
                type = UserType.Guest
            )
        ).build()
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BondApp)
            modules(appModule)
        }
    }
}
package com.example.emojisupport

import android.app.Application
import android.util.Log
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig

class EmojiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val fontRequest = FontRequest(
            PROVIDER_AUTHORITY,
            PROVIDER_PACKAGE,
            EMOJI_FONT,
            R.array.com_google_android_gms_fonts_certs)
        val config = FontRequestEmojiCompatConfig(this, fontRequest)
            .setReplaceAll(true)
            .setEmojiSpanIndicatorEnabled(true)
            .registerInitCallback(object : EmojiCompat.InitCallback() {
                override fun onInitialized() {
                    Log.i(javaClass.simpleName, "EmojiCompat initialized")
                }

                override fun onFailed(throwable: Throwable?) {
                    Log.e(javaClass.simpleName, "EmojiCompat initialization failed", throwable)
                }
            })
        EmojiCompat.init(config)
    }

    private companion object {
        private const val PROVIDER_AUTHORITY = "com.google.android.gms.fonts"
        private const val PROVIDER_PACKAGE = "com.google.android.gms"
        private const val EMOJI_FONT = "Noto Color Emoji Compat"
    }

}

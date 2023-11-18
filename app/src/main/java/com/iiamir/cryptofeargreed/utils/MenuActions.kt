package com.iiamir.cryptofeargreed.utils

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.iiamir.cryptofeargreed.utils.Constants.DEVELOPER_EMAIL
import com.iiamir.cryptofeargreed.utils.Constants.MARKET_URL
import com.iiamir.cryptofeargreed.utils.Constants.PACKAGE_NAME
import com.iiamir.cryptofeargreed.utils.Constants.SHOW_ALTER_NATIVE_WEBSITE
import com.iiamir.cryptofeargreed.utils.Constants.SHOW_EMAIL_APPLICATIONS
import com.iiamir.cryptofeargreed.utils.Constants.SHOW_GOOGLE_PLAY

object MenuActions {

    fun showBrowserAction(context: Context) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(SHOW_ALTER_NATIVE_WEBSITE))
        context.startActivity(browserIntent)
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun sendFeedbackAction(context: Context) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("$SHOW_EMAIL_APPLICATIONS$DEVELOPER_EMAIL")
        }
        context.startActivity(intent)
    }

    fun rateAppAction(context: Context) {
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(MARKET_URL + PACKAGE_NAME)))
        } catch (e: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(SHOW_GOOGLE_PLAY + PACKAGE_NAME)
                )
            )
        }
    }

}
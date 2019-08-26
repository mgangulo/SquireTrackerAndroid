package com.mabinogifanmade.squiretracker.utils

import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mabinogifanmade.squiretracker.R

class DialogUtils {
    companion object {
        fun showDialog(context: Context,
                          titleMessage:String,
                          messageMessage:String,
                          actionMessage:String,
                          action:DialogInterface.OnClickListener?,
                          cancelMessage:String,
                          cancel:DialogInterface.OnClickListener?
                          ) {
            MaterialAlertDialogBuilder(context)
                .setTitle(titleMessage)
                .setMessage(messageMessage)
                .setPositiveButton(actionMessage, action)
                .setNegativeButton(cancelMessage, cancel)
                .show();
        }
    }
}
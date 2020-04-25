package com.mabinogifanmade.squiretracker.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

class GeneralUtils {
    companion object{
        fun textToProgress(s:String):Int{
            if (!s.isNullOrEmpty() && s.toIntOrNull()!=null)
                return s.toIntOrNull()!!
            return 1
        }

        fun hideKeyboard(activity: Activity?) {
            try {
                val imm =
                    activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                if (activity.currentFocus != null && activity.currentFocus!!.windowToken != null)
                    imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


}
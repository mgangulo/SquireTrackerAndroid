package com.mabinogifanmade.squiretracker.utils

class GeneralUtils {
    companion object{
        fun textToProgress(s:String):Int{
            if (!s.isNullOrEmpty() && s.toIntOrNull()!=null)
                return s.toIntOrNull()!!
            return 1
        }

    }


}
package com.mabinogifanmade.squiretracker.utils

import android.os.Build
import java.util.stream.Collectors

class GeneralUtils {
    companion object{
        fun textToProgress(s:String):Int{
            if (!s.isNullOrEmpty() && s.toIntOrNull()!=null)
                return s.toIntOrNull()!!
            return 1
        }

        fun getUniqueValuesList(convoString: String): ArrayList<String> {
            val list: ArrayList<String> = ArrayList()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                list.addAll(
                    convoString
                        .chars().distinct()
                        .mapToObj({ c -> ConversationUtils.translateAbv(c.toChar()) })
                        .collect(Collectors.toList())
                )
            } else {
                val charSet: Set<Char> = convoString.toCharArray().toSet()
                for (c in charSet) {
                    list.add(ConversationUtils.translateAbv(c))
                }
            }
            return list
        }
    }


}
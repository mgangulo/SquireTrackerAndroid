package com.mabinogifanmade.squiretracker.utils

import android.content.Context
import com.mabinogifanmade.squiretracker.userdata.PlayerChar
import com.mabinogifanmade.squiretracker.userdata.UserGeneral

class UserUtils {
    companion object{
        fun saveNewPlayer(context:Context, newPlayer:PlayerChar){
            val user: UserGeneral? = ShrdPrfsUtils.getUserData(context!!)
            user?.playerChars?.add(newPlayer)
            ShrdPrfsUtils.saveUserData(context!!, user!!)
        }

        fun removePlayerAt(context: Context?, pos: Int) {
            val user: UserGeneral? = ShrdPrfsUtils.getUserData(context!!)
            user?.playerChars?.removeAt(pos)
            ShrdPrfsUtils.saveUserData(context,user!!)
        }

        fun editPlayerAt(context: Context,charPos: Int, playerName: String, server: String) {
            val user: UserGeneral? = ShrdPrfsUtils.getUserData(context)
            user?.playerChars?.get(charPos)?.charName = playerName
            user?.playerChars?.get(charPos)?.server = server
            ShrdPrfsUtils.saveUserData(context, user!!)
        }

        fun getCharPlayerAt(context: Context,pos:Int): PlayerChar {
            val user: UserGeneral? = ShrdPrfsUtils.getUserData(context)
            return user!!.playerChars.get(pos)
        }

        fun getCurrentCharPlayer(context: Context): PlayerChar {
            val user: UserGeneral? = ShrdPrfsUtils.getUserData(context)
            return user!!.getCurrentCharacter()
        }
    }
}
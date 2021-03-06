package com.mabinogifanmade.squiretracker.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.google.gson.Gson
import com.mabinogifanmade.squiretracker.BuildConfig
import com.mabinogifanmade.squiretracker.userdata.UserGeneral

class ShrdPrfsUtils {
    companion object {
        const val USER_DATA_KEY: String = "UserData"
        const val ERROR: String = "error"


        fun saveUserData(context: Context, userData: UserGeneral): Boolean {
            var success: Boolean = false
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            try {
                prefs.edit().putString(USER_DATA_KEY, Gson().toJson(userData)).apply()
                success = true
                if (BuildConfig.DEBUG){
                    Log.v("UserSaved",userData.toString())
                }
            } catch (e: NullPointerException) {
                e.printStackTrace()
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                return success
            }
        }

        fun getUserData(context: Context): UserGeneral? {
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            var userString = prefs.getString(USER_DATA_KEY, ERROR)
            if (userString!=null && !userString.equals(ERROR))
                return Gson().fromJson(userString, UserGeneral::class.java)
            else
                return null
        }

        fun userDataExist(context:Context):Boolean{
            val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.contains(USER_DATA_KEY)
        }
    }
}
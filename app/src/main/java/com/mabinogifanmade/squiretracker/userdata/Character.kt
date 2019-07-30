package com.mabinogifanmade.squiretracker.userdata

class Character(val charName: String, val server: String) {
    var daiProgress: Int = 0
    var eirlysProgress: Int = 0
    var elsieProgress: Int = 0
    var kaourProgress: Int = 0
    val squireOrder: ArrayList<Int> = arrayListOf(0,1,2,3)
}
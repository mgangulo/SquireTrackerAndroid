package com.mabinogifanmade.squiretracker.userdata

class UserGeneral(playerChar: PlayerChar) {
    val playerChars: ArrayList<PlayerChar> = arrayListOf(playerChar)
    var currentCharacter: Int = 0
    var prefersGrid: Boolean = false;

    fun getCurrentCharacter(): PlayerChar {
        if (currentCharacter >= playerChars.size)
            currentCharacter = 0
        return playerChars.get(currentCharacter)
    }
}
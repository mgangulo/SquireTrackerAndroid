package com.mabinogifanmade.squiretracker.userdata

class UserGeneral(playerChar: PlayerChar) {
    var playerChars: ArrayList<PlayerChar> = arrayListOf(playerChar)
    var currentCharacter: Int = 0
    var prefersGrid: Boolean = true;

    fun getCurrentCharacter(): PlayerChar {
        if (currentCharacter >= playerChars.size)
            currentCharacter = 0
        return playerChars.get(currentCharacter)
    }
}
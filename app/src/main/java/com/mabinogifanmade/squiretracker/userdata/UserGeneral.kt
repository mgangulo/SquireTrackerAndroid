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

    override fun toString(): String {
        val s = StringBuilder()
        s.append("prefers grid: ").append(prefersGrid).append("\n")
            .append("Current char: ").append(currentCharacter).append("\n")
        for (i in 0..playerChars.size-1) {
            s.append("Char at pos ").append(i).append(" :").append("\n")
                .append(playerChars.get(i).toString())
                .append("\n")
        }
        return s.toString()
    }
}
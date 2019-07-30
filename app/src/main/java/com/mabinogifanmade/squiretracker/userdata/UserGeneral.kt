package com.mabinogifanmade.squiretracker.userdata

class UserGeneral(character: Character) {
    val characters: ArrayList<Character> = arrayListOf(character)
    var currentCharacter: Int = 0
    var prefersGrid: Boolean = false;

    fun getCurrentCharacter(): Character {
        if (currentCharacter >= characters.size)
            currentCharacter = 0
        return characters.get(currentCharacter)
    }
}
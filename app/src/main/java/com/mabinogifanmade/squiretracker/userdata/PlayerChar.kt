package com.mabinogifanmade.squiretracker.userdata

import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.utils.ConversationUtils

class PlayerChar(var charName: String,
                 var server: String,
                 val daiProgress: Int,
                 val eirlysProgress: Int,
                 val elsieProgress: Int,
                 val kaourProgress: Int) {

    val squireProgress: HashMap<Int, Int> =
        hashMapOf(
            Squire.DAI.id to daiProgress,
            Squire.EIRLYS.id to eirlysProgress,
            Squire.ELSIE.id to elsieProgress,
            Squire.KAOUR.id to kaourProgress
        )

    constructor(charName: String, server: String)
            : this(charName,server,0,0,0,0) {
    }

    val squiresActive: HashSet<Int> =
        hashSetOf(
            Squire.DAI.id,
            Squire.EIRLYS.id,
            Squire.ELSIE.id,
            Squire.KAOUR.id
        )

    fun incSquireProgress(squire: Squire) {
        squireProgress.put(
            squire.id,
            ConversationUtils.getNumberInSequence(
                squireProgress.get(squire.id)!!+1,
                squire.sequenceConvo.length)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (other!=null && other is PlayerChar){
            return other.charName.equals(this@PlayerChar.charName)
                    && other.server.equals(this@PlayerChar.server)
        }
        return false
    }
}
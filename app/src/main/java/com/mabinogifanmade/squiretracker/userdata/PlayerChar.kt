package com.mabinogifanmade.squiretracker.userdata

import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.utils.ConversationUtils

class PlayerChar(val charName: String, val server: String) {
    val squireProgress: HashMap<Int, Int> =
        hashMapOf(
            Squire.DAI.id to 0,
            Squire.EIRLYS.id to 0,
            Squire.ELSIE.id to 0,
            Squire.KAOUR.id to 0
        )
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
}
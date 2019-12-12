package com.mabinogifanmade.squiretracker.userdata

import com.mabinogifanmade.squiretracker.R
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.utils.ConversationUtils

class PlayerChar(var charName: String,
                 var server: String,
                 val daiProgress: Int,
                 val eirlysProgress: Int,
                 val elsieProgress: Int,
                 val kaourProgress: Int,
                 var avatar:Int = R.drawable.ic_mabi_orange) {

    override fun toString(): String {
        val s = StringBuilder()
        s.append("Name: ").append(charName).append("\n")
            .append("Server: ").append(server).append("\n")
            .append("Dai progress: ").append(daiProgress)
            .append(" active? ").append(squiresActive.contains(Squire.DAI.id))
            .append("\n")
            .append("Eirlys progress: ").append(eirlysProgress)
            .append(" active? ").append(squiresActive.contains(Squire.EIRLYS.id))
            .append("\n")
            .append("Elsie progress: ").append(elsieProgress)
            .append(" active? ").append(squiresActive.contains(Squire.ELSIE.id))
            .append("\n")
            .append("Kaour progress: ").append(kaourProgress)
            .append(" active? ").append(squiresActive.contains(Squire.KAOUR.id)).append("\n")
        return s.toString()
    }

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

    fun setSquireProgress(squire: Squire,progress:Int) {
        squireProgress.put(
            squire.id,
            ConversationUtils.getNumberInSequence(
                progress,
                squire.sequenceConvo.length)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (other!=null && other is PlayerChar){
            return other.charName.toLowerCase().equals(this@PlayerChar.charName.toLowerCase())
                    && other.server.toLowerCase().equals(this@PlayerChar.server.toLowerCase())
        }
        return false
    }

    val squireHintSearch: HashMap<Int, String?> =
        hashMapOf(
            Squire.DAI.id to null,
            Squire.EIRLYS.id to null,
            Squire.ELSIE.id to null,
            Squire.KAOUR.id to null
        )

    val squireSeqSearch: HashMap<Int, String?> =
        hashMapOf(
            Squire.DAI.id to null,
            Squire.EIRLYS.id to null,
            Squire.ELSIE.id to null,
            Squire.KAOUR.id to null
        )
}
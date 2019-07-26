package com.mabinogifanmade.squiretracker.SquireData

interface SquireCommon {
    val squireName: String
    val sequenceHint: String
    fun hasHints(): Boolean
    fun sequenceConvo(): String
    fun imageSquire(): Int
}

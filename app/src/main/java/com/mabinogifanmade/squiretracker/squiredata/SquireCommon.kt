package com.mabinogifanmade.squiretracker.squiredata

interface SquireCommon {
    val squireName: String
    val sequenceHint: String
    val hasHint: Boolean
    val sequenceConvo: String
    val imageSquire: Int
    val specialOptions:HashMap<Int,ArrayList<SpecialOption>>
    val id:Int
}

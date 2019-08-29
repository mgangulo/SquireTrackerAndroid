package com.mabinogifanmade.squiretracker.squiredata

class SearchResult (val matches:Int, val suggest:String, val seqPos:Int?) {
    constructor(matches: Int,suggest: String ):this(matches,suggest,null)
}
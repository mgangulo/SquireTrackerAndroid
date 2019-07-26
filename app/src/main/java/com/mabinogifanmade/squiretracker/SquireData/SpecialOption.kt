package com.mabinogifanmade.squiretracker.SquireData

class SpecialOption(convoText: String, hint: String, percent: Float) {
    var convoText: String? = convoText
    var hint: String? = hint
    var percent: Float = percent

    constructor(convoText: String, percent: Float):this(convoText,"",percent){}
}
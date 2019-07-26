package com.mabinogifanmade.squiretracker.SquireData

enum class Squire : SquireCommon {

    DAI {
        override val squireName: String
            get() ="Dai"

        override val sequenceHint: String
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

        override fun hasHints(): Boolean {
            return true
        }

        override fun sequenceConvo(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun imageSquire(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    },

    EIRLYS {
        override val squireName: String
            get() = "Eirlys"

        override val sequenceHint: String
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

        override fun hasHints(): Boolean {
            return true;
        }

        override fun sequenceConvo(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun imageSquire(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    },

    ELSIE {
        override val squireName: String
            get() = "Elsie"

        override val sequenceHint: String
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

        override fun hasHints(): Boolean {
            return false
        }

        override fun sequenceConvo(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun imageSquire(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    },

    KAOUR {
        override val squireName: String
            get() = "Kaour"

        override val sequenceHint: String
            get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

        override fun hasHints(): Boolean {
            return false
        }

        override fun sequenceConvo(): String {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun imageSquire(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}


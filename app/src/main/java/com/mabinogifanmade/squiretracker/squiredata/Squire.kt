package com.mabinogifanmade.squiretracker.squiredata

import kotlin.collections.ArrayList
import kotlin.collections.HashMap

enum class Squire : SquireCommon {

    DAI {
        override val id: Int
            get() = 0

        override val specialOptions: HashMap<Int, ArrayList<SpecialOption>> by lazy {
            hashMapOf(
                2 to arrayListOf(
                    SpecialOption(Conversation.PLAYING,Conversation.AWKWARD,2f),
                    SpecialOption(Conversation.FASHION,Conversation.SERIOUS,33.3f),
                    SpecialOption(Conversation.DATING,Conversation.FUN,80f)
                ),
                3 to arrayListOf(
                    SpecialOption(Conversation.PLAYING,Conversation.SERIOUS,15f),
                    SpecialOption(Conversation.TRAINING,Conversation.AWKWARD,50f),
                    SpecialOption(Conversation.MISSION,Conversation.FUN,80f)
                ),
                4 to arrayListOf(
                    SpecialOption(Conversation.TRAINING,Conversation.FUN,15f),
                    SpecialOption(Conversation.FASHION,Conversation.SERIOUS,45f),
                    SpecialOption(Conversation.DATING,Conversation.SERIOUS,80f)
                )
            )
        }

        override val squireName: String
            get() = "Dai"

        override val sequenceHint: String
            get() = "SGSGASSASASAAGAGSSSASSSSGSSSAGSSAASGASGASGSGGGAGASSGGSSGAAAASASAASASASAGSAAAGSSAGGASSGSSGAGSAGASG"

        override val hasHint: Boolean
            get() = true

        override val sequenceConvo: String
            get()="PDPFTPPPPCPTPDMDFPPCFPPFFCFPCMDPCPFFCPFTPFPDMDPDPFCFFPFTTPPPPPFTCFPFPFPTPMCPTPFPMDTDFMFDFMFCPFCDF"

        override val imageSquire: Int
            get()=0

    },

    EIRLYS {
        override val id: Int
            get() = 1

        override val specialOptions: HashMap<Int, ArrayList<SpecialOption>> by lazy {
            hashMapOf(
                1 to arrayListOf(
                    SpecialOption(Conversation.TRAINING,Conversation.PLAYING,2.9f),
                    SpecialOption(Conversation.DATING,Conversation.MISSION,28.6f),
                    SpecialOption(Conversation.TRAINING,Conversation.COOKING,71.4f)
                ),
                2 to arrayListOf(
                    SpecialOption(Conversation.FASHION,Conversation.TRAINING,6.7f),
                    SpecialOption(Conversation.TRAINING,Conversation.PLAYING,33.3f),
                    SpecialOption(Conversation.MISSION,Conversation.COOKING,80f)
                ),
                3 to arrayListOf(
                    SpecialOption(Conversation.COOKING,Conversation.MISSION,20f),
                    SpecialOption(Conversation.COOKING,Conversation.TRAINING,40f),
                    SpecialOption(Conversation.PLAYING,Conversation.MISSION,80f)
                ),
                4 to arrayListOf(
                    SpecialOption(Conversation.DATING,Conversation.PLAYING,10f),
                    SpecialOption(Conversation.COOKING,Conversation.TRAINING,40f),
                    SpecialOption(Conversation.TRAINING,Conversation.COOKING,80f)
                )
            )
        }

        override val squireName: String
            get() = "Eirlys"

        override val sequenceHint: String
            get() = "FFFMTCDFPPPMDCFFPTPPMPCFPMDDTTCCFMDPPTTCPTCMPDTTPDFCPCCPMFMFMMFCFCFDFCDFMFDDMTMCFCMDTTDDMDDDPDFCT"

        override val hasHint: Boolean
            get() = true

        override val sequenceConvo: String
            get()="MPCPMTMMTMTFTTCDTFMTDFDDCTCPFFMMMTTTMCMMCPPTCPCCMCTMMTTDDMTCPFTTCMTMMMPTTPTMTMTFMTCMFPTTCCMTCMMDC"

        override val imageSquire: Int
            get()=0

    },

    ELSIE {
        override val id: Int
            get() = 2

        override val specialOptions: HashMap<Int, ArrayList<SpecialOption>> by lazy {
            hashMapOf(
                2 to arrayListOf(
                    SpecialOption(Conversation.TRAINING,0.7f),
                    SpecialOption(Conversation.PLAYING,46.7f),
                    SpecialOption(Conversation.COOKING,83.3f)
                ),
                3 to arrayListOf(
                    SpecialOption(Conversation.DATING,25f),
                    SpecialOption(Conversation.TRAINING,50f),
                    SpecialOption(Conversation.MISSION,80f)
                ),
                4 to arrayListOf(
                    SpecialOption(Conversation.TRAINING,25.5f),
                    SpecialOption(Conversation.PLAYING,50f),
                    SpecialOption(Conversation.COOKING,80f)
                )
            )
        }
        override val squireName: String
            get() = "Elsie"

        override val sequenceHint: String
            get() = ""

        override val hasHint: Boolean
            get() = false

        override val sequenceConvo: String
            get()="CPFDFPDDPMTPDPTPMCPPPDPCPDMFPPPDCDTDPCMTPPMDCDCPPDPTDPMPPPFDPPMTDDPPPFPTCPFPFDMPPDTPPFDMMFFFFDPPM"

        override val imageSquire: Int
            get()=0
    },

    KAOUR {
        override val id: Int
            get() = 3

        override val specialOptions: HashMap<Int, ArrayList<SpecialOption>> by lazy {
            hashMapOf(
                2 to arrayListOf(
                    SpecialOption(Conversation.TRAINING,0.7f),
                    SpecialOption(Conversation.PLAYING,53.3f),
                    SpecialOption(Conversation.MISSION,90f)
                ),
                3 to arrayListOf(
                    SpecialOption(Conversation.TRAINING,15f),
                    SpecialOption(Conversation.TRAINING,40f),
                    SpecialOption(Conversation.MISSION,90f)
                ),
                4 to arrayListOf(
                    SpecialOption(Conversation.DATING,10f),
                    SpecialOption(Conversation.TRAINING,56.7f),
                    SpecialOption(Conversation.PLAYING,93.3f)
                )
            )
        }
        override val squireName: String
            get() = "Kaour"

        override val sequenceHint: String
            get() = ""

        override val hasHint: Boolean
            get() = false

        override val sequenceConvo: String
            get()="PPTCPDTCDCMMFDCFDMMMFDMFPMFTDMMPDMFMTTTPTDFCTPFDPDCMPFCCCFFPPTFTMTCDFTPFDTMMCMMFCCTFCDMFDCDFDTMCT"

        override val imageSquire: Int
            get()=0

    }
}


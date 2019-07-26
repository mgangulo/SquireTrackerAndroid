package com.mabinogifanmade.squiretracker.SquireData

import kotlin.collections.ArrayList
import kotlin.collections.HashMap

enum class Squire : SquireCommon {

    DAI {
        override val specialOptions: HashMap<Int, ArrayList<SpecialOption>> by lazy {
            hashMapOf(
                2 to arrayListOf(
                    SpecialOption("Playing","Awkward",2f),
                    SpecialOption("Fashion","Serious",33.3f),
                    SpecialOption("Dating","Fun",80f)
                ),
                3 to arrayListOf(
                    SpecialOption("Playing","Serious",15f),
                    SpecialOption("Training","Awkward",50f),
                    SpecialOption("Mission","Fun",80f)
                ),
                4 to arrayListOf(
                    SpecialOption("Training","Fun",15f),
                    SpecialOption("Fashion","Serious",45f),
                    SpecialOption("Dating","Serious",80f)
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
        override val specialOptions: HashMap<Int, ArrayList<SpecialOption>> by lazy {
            hashMapOf(
                1 to arrayListOf(
                    SpecialOption("Training","Playing",2.9f),
                    SpecialOption("Dating","Mission",28.6f),
                    SpecialOption("Training","Cooking",71.4f)
                ),
                2 to arrayListOf(
                    SpecialOption("Fashion","Training",6.7f),
                    SpecialOption("Training","Playing",33.3f),
                    SpecialOption("Mission","Cooking",80f)
                ),
                3 to arrayListOf(
                    SpecialOption("Cooking","Mission",20f),
                    SpecialOption("Cooking","Training",40f),
                    SpecialOption("Playing","Mission",80f)
                ),
                4 to arrayListOf(
                    SpecialOption("Dating","Playing",10f),
                    SpecialOption("Cooking","Training",40f),
                    SpecialOption("Training","Cooking",80f)
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
        override val specialOptions: HashMap<Int, ArrayList<SpecialOption>> by lazy {
            hashMapOf(
                2 to arrayListOf(
                    SpecialOption("Training",0.7f),
                    SpecialOption("Playing",46.7f),
                    SpecialOption("Cooking",83.3f)
                ),
                3 to arrayListOf(
                    SpecialOption("Dating",25f),
                    SpecialOption("Training",50f),
                    SpecialOption("Mission",80f)
                ),
                4 to arrayListOf(
                    SpecialOption("Training",25.5f),
                    SpecialOption("Playing",50f),
                    SpecialOption("Cooking",80f)
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
        override val specialOptions: HashMap<Int, ArrayList<SpecialOption>> by lazy {
            hashMapOf(
                2 to arrayListOf(
                    SpecialOption("Training",0.7f),
                    SpecialOption("Playing",53.3f),
                    SpecialOption("Mission",90f)
                ),
                3 to arrayListOf(
                    SpecialOption("Training",15f),
                    SpecialOption("Training",40f),
                    SpecialOption("Mission",90f)
                ),
                4 to arrayListOf(
                    SpecialOption("Dating",10f),
                    SpecialOption("Training",56.7f),
                    SpecialOption("Playing",93.3f)
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


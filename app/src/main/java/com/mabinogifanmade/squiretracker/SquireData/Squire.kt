package com.mabinogifanmade.squiretracker.SquireData

enum class Squire : SquireCommon {

    DAI {
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


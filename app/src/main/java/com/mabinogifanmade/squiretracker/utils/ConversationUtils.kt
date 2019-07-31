package com.mabinogifanmade.squiretracker.utils

import android.content.Context
import com.mabinogifanmade.squiretracker.squiredata.Conversation
import com.mabinogifanmade.squiretracker.squiredata.Squire
import com.mabinogifanmade.squiretracker.userdata.UserGeneral

class ConversationUtils {
    companion object {
        fun translateAbv(key: Char): String {
            when (key) {
                Conversation.AWKWARD_ABV -> return Conversation.AWKWARD

                Conversation.MISSION_ABV -> return Conversation.MISSION

                Conversation.TRAINING_ABV -> return Conversation.TRAINING

                Conversation.PLAYING_ABV -> return Conversation.PLAYING

                Conversation.COOKING_ABV -> return Conversation.COOKING

                Conversation.FASHION_ABV -> return Conversation.FASHION

                Conversation.DATING_ABV -> return Conversation.DATING

                Conversation.SERIOUS_ABV -> return Conversation.SERIOUS

                Conversation.FUN_ABV -> return Conversation.FUN
            }
            return ""
        }

        fun translateOption(key: String): Char {
            when (key) {
                Conversation.AWKWARD -> return Conversation.AWKWARD_ABV

                Conversation.MISSION -> return Conversation.MISSION_ABV

                Conversation.TRAINING -> return Conversation.TRAINING_ABV

                Conversation.PLAYING -> return Conversation.PLAYING_ABV

                Conversation.COOKING -> return Conversation.COOKING_ABV

                Conversation.FASHION -> return Conversation.FASHION_ABV

                Conversation.DATING -> return Conversation.DATING_ABV

                Conversation.SERIOUS -> return Conversation.SERIOUS_ABV

                Conversation.FUN -> return Conversation.FUN_ABV
            }
            return '-'
        }

        fun translateCurrentAbv(squire: Squire, isHint: Boolean, currentProgress: Int): String {
            return translateAbvWithOffset(squire, isHint, currentProgress, 0)
        }

        fun translateNextAbv(squire: Squire, isHint: Boolean, currentProgress: Int): String {
            return translateAbvWithOffset(squire, isHint, currentProgress, 1)
        }

        fun translatePreviousAbv(squire: Squire, isHint: Boolean, currentProgress: Int): String {
            return translateAbvWithOffset(squire, isHint, currentProgress, -1)
        }

        fun translateAbvWithOffset(
            squire: Squire, isHint: Boolean, currentProgress: Int,
            offset: Int
        ): String {
            val progress: Int = currentProgress + offset
            val currentSequence: String =
                when (isHint) {
                    true -> squire.sequenceHint
                    false -> squire.sequenceConvo
                }
            return translateAbv(currentSequence[getNumberInSequence(progress,currentSequence.length)])
        }

        fun getNumberInSequence(progress: Int, lenght: Int): Int {
            return when {
                (progress in 1..(lenght - 1)) -> progress
                (progress < 0) -> (lenght - 1)
                else -> 0
            }
        }
    }

}
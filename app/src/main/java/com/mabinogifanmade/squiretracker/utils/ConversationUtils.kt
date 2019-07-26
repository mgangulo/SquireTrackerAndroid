package com.mabinogifanmade.squiretracker.utils

import com.mabinogifanmade.squiretracker.squiredata.Conversation

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
    }
}
package com.mabinogifanmade.squiretracker.utils

import com.mabinogifanmade.squiretracker.squiredata.Conversation

class ConversationUtils {
    companion object {
        fun translate(key: String): String {
            when (key) {
                Conversation.AWKWARD_ABV -> return Conversation.AWKWARD

                Conversation.MISSION_ABV -> return Conversation.MISSION

                Conversation.TRAINING_ABV -> return Conversation.TRAINING

                Conversation.PLAYING_ABV -> return Conversation.PLAYING

                Conversation.COOKING_ABV -> return Conversation.COOKING

                Conversation.FASHION_ABV -> return Conversation.FASHION

                Conversation.DATING_ABV -> return Conversation.DATING

                Conversation.SERIOUS_ABV -> return Conversation.SERIOUS_ABV

                Conversation.FUN_ABV -> return Conversation.FUN_ABV
            }
            return ""
        }
    }
}
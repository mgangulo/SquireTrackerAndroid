package com.mabinogifanmade.squiretracker.utils

import android.os.Build
import com.mabinogifanmade.squiretracker.squiredata.Conversation
import com.mabinogifanmade.squiretracker.squiredata.SearchResult
import com.mabinogifanmade.squiretracker.squiredata.Squire
import java.util.regex.Pattern
import java.util.stream.Collectors


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
            return translateAbv(currentSequence[getNumberInSequence(progress, currentSequence.length)])
        }

        fun getNumberInSequence(progress: Int, lenght: Int): Int {
            return when {
                (progress in 1..(lenght - 1)) -> progress
                (progress < 0) -> (lenght - 1)
                else -> 0
            }
        }

        fun getUniqueConvoList(convoString: String): ArrayList<String> {
            val list: ArrayList<String> = ArrayList()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                list.addAll(
                    convoString
                        .chars().distinct()
                        .mapToObj({ c -> translateAbv(c.toChar()) })
                        .collect(Collectors.toList())
                )
            } else {
                val charSet: Set<Char> = convoString.toCharArray().toSet()
                for (c in charSet) {
                    list.add(translateAbv(c))
                }
            }
            return list
        }

        fun searchOnSeq(key: String, seq: String): SearchResult {
            val nextOptionBuilder: StringBuilder = StringBuilder()
            val word = Pattern.compile(key)
            val match = word.matcher(seq)
            var matchPos: Int = 0
            while (match.find()) {
                matchPos = match.start() + 1
                val nextOptionIndex = getNumberInSequence(match.end()-1, seq.length)
                nextOptionBuilder.append(seq[nextOptionIndex])
            }
            return generateSearchResult(nextOptionBuilder, matchPos)
        }

        fun searchOnSeqWithHint(
            keySeq: String?,
            seq: String,
            keyHint: String,
            hint: String,
            hintSuggestion: Boolean
        ): SearchResult {
            val nextOptionBuilder: StringBuilder = StringBuilder()
            val wordHint = Pattern.compile(keyHint)
            val matchHint = wordHint.matcher(hint)
            var matchPos: Int = 0
            while (matchHint.find()) {
                matchPos = matchHint.start() + 1
                val nextOptionIndex = getNumberInSequence(matchHint.end()-1, hint.length)
                if (!keySeq.isNullOrEmpty()) {
                    var seqSub = seq.substring(matchHint.start(), matchHint.end())
                    if (seqSub.length > keySeq.length){
                        seqSub = seqSub.substring(0,keySeq.length)
                    }
                    if (seqSub.equals(keySeq))

                    if (seqSub.equals(keySeq)) {
                        if (!hintSuggestion) {
                            nextOptionBuilder.append(seq[nextOptionIndex])
                        }
                        else {
                            nextOptionBuilder.append(hint[nextOptionIndex])
                        }
                    }
                } else {
                    nextOptionBuilder.append(seq[nextOptionIndex])
                }
            }
            return generateSearchResult(nextOptionBuilder, matchPos)
        }

        private fun generateSearchResult(nextOptionBuilder: StringBuilder, matchPos: Int): SearchResult {
            val nextOption: String = nextOptionBuilder.toString()
            val nextUnique = getUniqueConvoList(nextOption)
            nextOptionBuilder.clear()
            for (i in 0..nextUnique.size-1) {
                nextOptionBuilder.append(nextUnique[i])
                if (i < nextUnique.size - 1)
                    nextOptionBuilder.append(", ")
            }
            return if (nextOption.length > 1) {
                SearchResult(nextOption.length, nextOptionBuilder.toString(), matchPos)
            } else
                SearchResult(nextOption.length, nextOptionBuilder.toString())
        }

        private fun getSearchSubstring(key:String,sub:String){}

    }
}
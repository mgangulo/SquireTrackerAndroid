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

        fun getNumberInSequenceWithOffset(progress: Int, lenght: Int): Int {
            var progress2 = progress
            while (progress2 > lenght) {
                progress2 = progress2 - lenght
            }
            return getNumberInSequence(progress2, lenght)
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
                matchPos = getNumberInSequence(match.end(), seq.length) + 1
                val nextOptionIndex = getNumberInSequence(match.end(), seq.length)
                nextOptionBuilder.append(seq[nextOptionIndex])
            }
            val circIndex = searchCircular(key, seq, nextOptionBuilder)
            matchPos = when (circIndex) {
                -1 -> matchPos
                else -> circIndex
            }
            return generateSearchResult(nextOptionBuilder, matchPos)
        }

        private fun searchCircular(
            keySeq: String,
            seq: String,
            nextOptionBuilder: StringBuilder
        ): Int {
            if (keySeq.length > 1) {
                val lastSeq = seq.substring(seq.length - keySeq.length, seq.length)
                val circSeqStartIndex = lastSeq.length - 1
                val circSeqEndIndex = lastSeq.length
                val circularSeq = StringBuilder()
                    .append(lastSeq)
                    .append(seq.substring(0, keySeq.length))
                    .toString()
                val word = Pattern.compile(keySeq)
                val match = word.matcher(circularSeq)
                while (match.find()) {
                    if (circSeqStartIndex in match.start()..match.end() &&
                        circSeqEndIndex in match.start()..match.end()
                    ) {
                        val nextOptionIndex = getNumberInSequence(match.end() - 1, circularSeq.length)
                        nextOptionBuilder.append(circularSeq[nextOptionIndex])
                        return getNumberInSequenceWithOffset(
                            seq.length - circSeqEndIndex + match.end(), seq.length
                        ) + 1
                    }
                }
            }
            return -1
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
                if (!keySeq.isNullOrEmpty()) {
                    var seqSub = seq.substring(matchHint.start(), matchHint.end())
                    if (seqSub.length > keySeq.length) {
                        seqSub = seqSub.substring(0, keySeq.length)
                    }
                    if (seqSub.equals(keySeq))

                        if (seqSub.equals(keySeq)) {
                            if (!hintSuggestion) {
                                matchPos = getNumberInSequence(matchHint.end()-1, hint.length) + 1
                                val nextOptionIndex = getNumberInSequence(matchHint.end() - 1, seq.length)
                                nextOptionBuilder.append(seq[nextOptionIndex])
                            } else {
                                matchPos = getNumberInSequence(matchHint.end(), hint.length) + 1
                                val nextOptionIndex = getNumberInSequence(matchHint.end(), hint.length)
                                nextOptionBuilder.append(hint[nextOptionIndex])
                            }
                        }
                } else {
                    matchPos = getNumberInSequence(matchHint.end(), hint.length) + 1
                    val nextOptionIndex = getNumberInSequence(matchHint.end() - 1, seq.length)
                    nextOptionBuilder.append(seq[nextOptionIndex])
                }
            }
            val circIndex = searchCircularHint(keySeq, seq, keyHint, hint, hintSuggestion, nextOptionBuilder)
            matchPos = when (circIndex) {
                -1 -> matchPos
                else -> circIndex
            }
            return generateSearchResult(nextOptionBuilder, matchPos)
        }

        private fun searchCircularHint(
            keySeq: String?,
            seq: String,
            keyHint: String,
            hint: String,
            hintSuggestion: Boolean,
            nextOptionBuilder: StringBuilder
        ): Int {
            val lastSeq = seq.substring(seq.length - keyHint.length, seq.length)
            val circSeqStartIndex = lastSeq.length - 1
            val circSeqEndIndex = lastSeq.length
            val circularSeq = StringBuilder()
                .append(lastSeq)
                .append(seq.substring(0, keyHint.length))
                .toString()
            val lastHint = hint.substring(hint.length - keyHint.length, hint.length)
            val circHintStartIndex = lastHint.length - 1
            val circHintEndIndex = lastHint.length
            val circularHint = StringBuilder()
                .append(lastHint)
                .append(hint.substring(0, keyHint.length))
                .toString()
            var matchPos:Int = -1
            if (keyHint.length > 1) {
                val wordHint = Pattern.compile(keyHint)
                val matchHint = wordHint.matcher(circularHint)
                while (matchHint.find()) {
                    if (circHintStartIndex in matchHint.start()..matchHint.end() - 1 &&
                        circHintEndIndex in matchHint.start()..matchHint.end() - 1
                    ) {
                        if (!keySeq.isNullOrEmpty()) {
                            var seqSub = circularSeq.substring(matchHint.start(), matchHint.end())
                            if (seqSub.length > keySeq.length) {
                                seqSub = seqSub.substring(0, keySeq.length)
                            }
                            if (seqSub.equals(keySeq)) {
                                if (!hintSuggestion) {
                                    val nextOptionIndex =
                                        getNumberInSequence(matchHint.end() - 1, circularSeq.length)
                                    nextOptionBuilder.append(circularSeq[nextOptionIndex])
                                    matchPos = getNumberInSequenceWithOffset(
                                            hint.length - circHintEndIndex + matchHint.end()-1, hint.length
                                    ) + 1
                                } else {
                                    val nextOptionIndex = getNumberInSequence(matchHint.end(), circularHint.length)
                                    nextOptionBuilder.append(circularHint[nextOptionIndex])
                                    matchPos = getNumberInSequenceWithOffset(
                                        hint.length - circHintEndIndex + matchHint.end(), hint.length
                                    ) + 1
                                }
                            }
                        } else {
                            val nextOptionIndex = getNumberInSequence(matchHint.end() - 1, circularSeq.length)
                            nextOptionBuilder.append(circularSeq[nextOptionIndex])
                            matchPos = getNumberInSequenceWithOffset(
                                hint.length - circHintEndIndex + matchHint.end()-1, hint.length
                            ) + 1
                        }
                    }
                }
            }
            return matchPos
        }

        private fun generateSearchResult(nextOptionBuilder: StringBuilder, matchPos: Int): SearchResult {
            val nextOption: String = nextOptionBuilder.toString()
            val nextUnique = getUniqueConvoList(nextOption)
            nextOptionBuilder.clear()
            for (i in 0..nextUnique.size - 1) {
                nextOptionBuilder.append(nextUnique[i])
                if (i < nextUnique.size - 1)
                    nextOptionBuilder.append(", ")
            }
            return if (nextOption.length > 1) {
                SearchResult(nextOption.length, nextOptionBuilder.toString())
            } else
                SearchResult(nextOption.length, nextOptionBuilder.toString(), matchPos)
        }

    }
}
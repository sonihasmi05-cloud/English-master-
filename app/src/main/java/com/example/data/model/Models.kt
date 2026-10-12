package com.example.data.model

data class LessonExample(
    val en: String,
    val hi: String,
    val pronunciationHi: String
)

data class DialogueTurn(
    val speaker: String, // "A" or "B" (e.g., "Interviewer", "Candidate" or "Shopkeeper", "Customer")
    val speakerName: String,
    val textEn: String,
    val textHi: String,
    val pronunciationHi: String = ""
)

data class GrammarMistake(
    val errorSnippet: String,
    val correction: String,
    val explanationHi: String
)

data class SpeechAnalysisResult(
    val userSpeech: String,
    val correctedEnglish: String,
    val fluencyScore: Int, // 0 - 100
    val grammarScore: Int, // 0 - 100
    val feedbackHi: String,
    val mistakes: List<GrammarMistake> = emptyList()
)

data class LeaderboardUser(
    val rank: Int,
    val name: String,
    val city: String,
    val streak: Int,
    val points: Int,
    val isCurrentUser: Boolean = false
)

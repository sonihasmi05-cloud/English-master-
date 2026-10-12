package com.example.data.repository

import com.example.data.local.EnglishMasterDatabase
import com.example.data.local.LessonEntity
import com.example.data.local.QuizQuestionEntity
import com.example.data.local.SavedTranslationEntity
import com.example.data.local.TeacherMessageEntity
import com.example.data.local.UserProfileEntity
import com.example.data.local.VocabularyEntity
import com.example.data.seed.SeedData
import com.example.service.GrammarAiEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class EnglishMasterRepository(private val database: EnglishMasterDatabase) {

    private val userDao = database.userDao()
    private val lessonDao = database.lessonDao()
    private val vocabDao = database.vocabDao()
    private val quizDao = database.quizDao()
    private val convDao = database.conversationDao()
    private val transDao = database.translationDao()
    private val chatDao = database.teacherChatDao()
    private val badgeDao = database.badgeDao()

    val userProfile: Flow<UserProfileEntity?> = userDao.getUserProfile()
    val allLessons: Flow<List<LessonEntity>> = lessonDao.getAllLessons()
    val courseLessons: Flow<List<LessonEntity>> = lessonDao.getLessonsByModule("COURSE")
    val grammarLessons: Flow<List<LessonEntity>> = lessonDao.getLessonsByModule("GRAMMAR")
    val allVocab: Flow<List<VocabularyEntity>> = vocabDao.getAllVocab()
    val favoriteVocab: Flow<List<VocabularyEntity>> = vocabDao.getFavoriteVocab()
    val allQuizzes: Flow<List<QuizQuestionEntity>> = quizDao.getAllQuizzes()
    val allConversations = convDao.getAllConversations()
    val savedTranslations: Flow<List<SavedTranslationEntity>> = transDao.getAllSavedTranslations()
    val teacherMessages: Flow<List<TeacherMessageEntity>> = chatDao.getAllMessages()
    val badges = badgeDao.getAllBadges()
    val completedLessonCount = lessonDao.getCompletedLessonCount()

    suspend fun seedInitialDataIfNeeded() = withContext(Dispatchers.IO) {
        val existingProfile = userDao.getUserProfile().firstOrNull()
        if (existingProfile == null) {
            userDao.insertOrUpdateProfile(SeedData.initialProfile)
            lessonDao.insertAll(SeedData.initialLessons)
            vocabDao.insertAll(SeedData.initialVocabulary)
            convDao.insertAll(SeedData.initialConversations)
            quizDao.insertAll(SeedData.initialQuizzes)
            badgeDao.insertAll(SeedData.initialBadges)

            // Seed initial greeting message from Teacher
            chatDao.insertMessage(
                TeacherMessageEntity(
                    sender = "TEACHER",
                    messageText = "Hello! I am your 24/7 AI English Teacher 'Guru AI'. How can I help you today? You can ask me grammar rules, sentence corrections, or vocabulary!",
                    hindiExplanation = "नमस्ते! मैं आपका 24/7 AI इंग्लिश टीचर हूँ। आप मुझसे व्याकरण के नियम, वाक्यों की जाँच या अनुवाद कभी भी पूछ सकते हैं!"
                )
            )
        }
    }

    suspend fun setLessonCompleted(lessonId: String, completed: Boolean) = withContext(Dispatchers.IO) {
        lessonDao.setLessonCompleted(lessonId, completed)
        if (completed) {
            userDao.addXp(50)
        }
    }

    suspend fun toggleBookmark(lessonId: String) = withContext(Dispatchers.IO) {
        lessonDao.toggleBookmark(lessonId)
    }

    suspend fun toggleVocabFavorite(id: Int) = withContext(Dispatchers.IO) {
        vocabDao.toggleFavorite(id)
    }

    suspend fun saveTranslation(hindi: String, english: String, expHi: String, pronHi: String) = withContext(Dispatchers.IO) {
        transDao.insertTranslation(
            SavedTranslationEntity(
                hindiText = hindi,
                englishText = english,
                explanationHi = expHi,
                pronunciationHi = pronHi
            )
        )
    }

    suspend fun deleteTranslation(id: Int) = withContext(Dispatchers.IO) {
        transDao.deleteTranslation(id)
    }

    suspend fun sendTeacherMessage(userText: String) = withContext(Dispatchers.IO) {
        // Save user message
        chatDao.insertMessage(
            TeacherMessageEntity(
                sender = "USER",
                messageText = userText
            )
        )

        // Generate response via intelligent grammar engine
        val (enResp, hiResp) = GrammarAiEngine.getTeacherResponse(userText)

        // Also check if user provided a sentence to correct
        val analysis = GrammarAiEngine.analyzeSpokenSentence(userText)
        val correctedSnippet = if (analysis.mistakes.isNotEmpty()) {
            analysis.correctedEnglish
        } else ""

        chatDao.insertMessage(
            TeacherMessageEntity(
                sender = "TEACHER",
                messageText = enResp,
                hindiExplanation = hiResp,
                correctedVersion = correctedSnippet
            )
        )

        userDao.addXp(15)
    }

    suspend fun clearTeacherChat() = withContext(Dispatchers.IO) {
        chatDao.clearChat()
        chatDao.insertMessage(
            TeacherMessageEntity(
                sender = "TEACHER",
                messageText = "Hello! I am your 24/7 AI English Teacher 'Guru AI'. What would you like to learn now?",
                hindiExplanation = "नमस्ते! मैं आपका 24/7 AI इंग्लिश टीचर हूँ। अब आप क्या सीखना चाहेंगे?"
            )
        )
    }

    suspend fun addXp(points: Int) = withContext(Dispatchers.IO) {
        userDao.addXp(points)
    }

    suspend fun updateStreak(streak: Int) = withContext(Dispatchers.IO) {
        userDao.updateStreak(streak)
    }

    suspend fun updateMinutes(minutes: Int) = withContext(Dispatchers.IO) {
        userDao.updateMinutesSpent(minutes)
    }

    suspend fun updateUserProfile(profile: UserProfileEntity) = withContext(Dispatchers.IO) {
        userDao.insertOrUpdateProfile(profile)
    }

    suspend fun insertLesson(lesson: LessonEntity) = withContext(Dispatchers.IO) {
        lessonDao.insertLesson(lesson)
    }

    suspend fun deleteLesson(id: String) = withContext(Dispatchers.IO) {
        lessonDao.deleteLesson(id)
    }

    suspend fun insertVocab(vocab: VocabularyEntity) = withContext(Dispatchers.IO) {
        vocabDao.insertVocab(vocab)
    }

    suspend fun deleteVocab(id: Int) = withContext(Dispatchers.IO) {
        vocabDao.deleteVocab(id)
    }

    suspend fun insertQuiz(quiz: QuizQuestionEntity) = withContext(Dispatchers.IO) {
        quizDao.insertQuiz(quiz)
    }

    suspend fun deleteQuiz(id: Int) = withContext(Dispatchers.IO) {
        quizDao.deleteQuiz(id)
    }

    suspend fun markConversationCompleted(id: String) = withContext(Dispatchers.IO) {
        convDao.setConversationCompleted(id, true)
        userDao.addXp(40)
    }
}

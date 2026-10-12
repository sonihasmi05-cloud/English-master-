package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.EnglishMasterDatabase
import com.example.data.local.LessonEntity
import com.example.data.local.QuizQuestionEntity
import com.example.data.local.SavedTranslationEntity
import com.example.data.local.TeacherMessageEntity
import com.example.data.local.UserProfileEntity
import com.example.data.local.VocabularyEntity
import com.example.data.model.SpeechAnalysisResult
import com.example.data.repository.EnglishMasterRepository
import com.example.service.GeminiTranslationResult
import com.example.service.GeminiTranslationService
import com.example.service.GrammarAiEngine
import com.example.service.SpeechManager
import com.example.service.TtsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class Screen {
    HOME,
    COURSES,
    VOCABULARY,
    SPEAKING,
    GRAMMAR,
    TRANSLATOR,
    CONVERSATIONS,
    QUIZ,
    AI_TEACHER,
    ADMIN,
    PROFILE,
    LESSON_DETAIL,
    CONVERSATION_DETAIL
}

class EnglishMasterViewModel(application: Application) : AndroidViewModel(application) {

    private val database = EnglishMasterDatabase.getDatabase(application)
    private val repository = EnglishMasterRepository(database)
    val ttsManager = TtsManager(application)
    val speechManager = SpeechManager(application)

    val userProfile: StateFlow<UserProfileEntity?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val allLessons = repository.allLessons
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val courseLessons = repository.courseLessons
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val grammarLessons = repository.grammarLessons
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allVocab = repository.allVocab
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favoriteVocab = repository.favoriteVocab
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allQuizzes = repository.allQuizzes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allConversations = repository.allConversations
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val savedTranslations = repository.savedTranslations
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val teacherMessages = repository.teacherMessages
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val badges = repository.badges
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val completedLessonCount = repository.completedLessonCount
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    // Navigation and selected item state
    private val _currentScreen = MutableStateFlow(Screen.HOME)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _screenBackStack = mutableListOf<Screen>()

    private val _selectedLesson = MutableStateFlow<LessonEntity?>(null)
    val selectedLesson: StateFlow<LessonEntity?> = _selectedLesson.asStateFlow()

    private val _selectedConversation = MutableStateFlow<com.example.data.local.ConversationEntity?>(null)
    val selectedConversation: StateFlow<com.example.data.local.ConversationEntity?> = _selectedConversation.asStateFlow()

    // Translator state
    val geminiTranslationService = GeminiTranslationService()

    private val _isEnglishToHindiMode = MutableStateFlow(true)
    val isEnglishToHindiMode: StateFlow<Boolean> = _isEnglishToHindiMode.asStateFlow()

    private val _englishInput = MutableStateFlow("")
    val englishInput: StateFlow<String> = _englishInput.asStateFlow()

    private val _isGeminiTranslating = MutableStateFlow(false)
    val isGeminiTranslating: StateFlow<Boolean> = _isGeminiTranslating.asStateFlow()

    private val _geminiResult = MutableStateFlow<GeminiTranslationResult?>(null)
    val geminiResult: StateFlow<GeminiTranslationResult?> = _geminiResult.asStateFlow()

    private val _hindiInput = MutableStateFlow("")
    val hindiInput: StateFlow<String> = _hindiInput.asStateFlow()

    private val _currentTranslation = MutableStateFlow<GrammarAiEngine.TranslationOutput?>(null)
    val currentTranslation: StateFlow<GrammarAiEngine.TranslationOutput?> = _currentTranslation.asStateFlow()

    // Speaking practice state
    private val _speechAnalysis = MutableStateFlow<SpeechAnalysisResult?>(null)
    val speechAnalysis: StateFlow<SpeechAnalysisResult?> = _speechAnalysis.asStateFlow()

    // Quiz state
    private val _activeQuizCategory = MutableStateFlow("Daily")
    val activeQuizCategory: StateFlow<String> = _activeQuizCategory.asStateFlow()

    private val _quizCurrentIndex = MutableStateFlow(0)
    val quizCurrentIndex: StateFlow<Int> = _quizCurrentIndex.asStateFlow()

    private val _quizSelectedOption = MutableStateFlow<Int?>(null)
    val quizSelectedOption: StateFlow<Int?> = _quizSelectedOption.asStateFlow()

    private val _quizScore = MutableStateFlow(0)
    val quizScore: StateFlow<Int> = _quizScore.asStateFlow()

    private val _quizFinished = MutableStateFlow(false)
    val quizFinished: StateFlow<Boolean> = _quizFinished.asStateFlow()

    init {
        viewModelScope.launch {
            repository.seedInitialDataIfNeeded()
        }
    }

    fun navigateTo(screen: Screen) {
        if (_currentScreen.value != screen) {
            _screenBackStack.add(_currentScreen.value)
            _currentScreen.value = screen
        }
    }

    fun navigateBack(): Boolean {
        return if (_screenBackStack.isNotEmpty()) {
            _currentScreen.value = _screenBackStack.removeAt(_screenBackStack.size - 1)
            true
        } else if (_currentScreen.value != Screen.HOME) {
            _currentScreen.value = Screen.HOME
            true
        } else {
            false
        }
    }

    fun openLesson(lesson: LessonEntity) {
        _selectedLesson.value = lesson
        navigateTo(Screen.LESSON_DETAIL)
    }

    fun openConversation(conversation: com.example.data.local.ConversationEntity) {
        _selectedConversation.value = conversation
        navigateTo(Screen.CONVERSATION_DETAIL)
    }

    fun toggleLessonCompleted(lessonId: String, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.setLessonCompleted(lessonId, !currentStatus)
            _selectedLesson.value = _selectedLesson.value?.copy(isCompleted = !currentStatus)
        }
    }

    fun toggleLessonBookmark(lessonId: String) {
        viewModelScope.launch {
            repository.toggleBookmark(lessonId)
            _selectedLesson.value?.let {
                _selectedLesson.value = it.copy(isBookmarked = !it.isBookmarked)
            }
        }
    }

    fun toggleVocabFavorite(id: Int) {
        viewModelScope.launch {
            repository.toggleVocabFavorite(id)
        }
    }

    fun setTranslationMode(englishToHindi: Boolean) {
        _isEnglishToHindiMode.value = englishToHindi
    }

    fun setEnglishInput(text: String) {
        _englishInput.value = text
    }

    fun translateEnglishToHindi(customText: String? = null) {
        val text = customText ?: _englishInput.value
        if (text.isNotBlank()) {
            if (customText != null) {
                _englishInput.value = customText
            }
            viewModelScope.launch {
                _isGeminiTranslating.value = true
                val res = geminiTranslationService.translateEnglishToHindi(text)
                _geminiResult.value = res
                _isGeminiTranslating.value = false
                repository.addXp(15)
            }
        }
    }

    fun saveGeminiTranslation() {
        val res = _geminiResult.value ?: return
        val en = _englishInput.value.ifBlank { "English text" }
        viewModelScope.launch {
            repository.saveTranslation(
                hindi = res.hindiText,
                english = en,
                expHi = res.grammaticalExplanationHi,
                pronHi = res.pronunciationHi
            )
        }
    }

    fun setHindiInput(text: String) {
        _hindiInput.value = text
    }

    fun performTranslation(customQuery: String? = null) {
        val query = customQuery ?: _hindiInput.value
        if (query.isNotBlank()) {
            val result = GrammarAiEngine.translateHindiToEnglish(query)
            _currentTranslation.value = result
            if (customQuery != null) {
                _hindiInput.value = customQuery
            }
        }
    }

    fun saveCurrentTranslation() {
        val trans = _currentTranslation.value ?: return
        val hi = _hindiInput.value.ifBlank { "सामान्य वाक्य" }
        viewModelScope.launch {
            repository.saveTranslation(
                hindi = hi,
                english = trans.english,
                expHi = trans.grammarExplanationHi,
                pronHi = trans.pronunciationHi
            )
        }
    }

    fun deleteSavedTranslation(id: Int) {
        viewModelScope.launch {
            repository.deleteTranslation(id)
        }
    }

    fun analyzeSpeechText(sentence: String) {
        val analysis = GrammarAiEngine.analyzeSpokenSentence(sentence)
        _speechAnalysis.value = analysis
        viewModelScope.launch {
            repository.addXp(20)
        }
    }

    fun clearSpeechAnalysis() {
        _speechAnalysis.value = null
        speechManager.clear()
    }

    fun sendTeacherMessage(message: String) {
        if (message.isBlank()) return
        viewModelScope.launch {
            repository.sendTeacherMessage(message)
        }
    }

    fun clearTeacherChat() {
        viewModelScope.launch {
            repository.clearTeacherChat()
        }
    }

    fun setQuizCategory(category: String) {
        _activeQuizCategory.value = category
        resetQuiz()
    }

    fun selectQuizOption(index: Int) {
        _quizSelectedOption.value = index
    }

    fun submitQuizAnswer(correctIndex: Int) {
        if (_quizSelectedOption.value == correctIndex) {
            _quizScore.value += 10
            viewModelScope.launch {
                repository.addXp(10)
            }
        }
    }

    fun nextQuizQuestion(totalQuestions: Int) {
        if (_quizCurrentIndex.value + 1 < totalQuestions) {
            _quizCurrentIndex.value += 1
            _quizSelectedOption.value = null
        } else {
            _quizFinished.value = true
        }
    }

    fun resetQuiz() {
        _quizCurrentIndex.value = 0
        _quizSelectedOption.value = null
        _quizScore.value = 0
        _quizFinished.value = false
    }

    fun speak(text: String, isHindi: Boolean = false) {
        ttsManager.speak(text, isHindi)
    }

    fun stopSpeaking() {
        ttsManager.stop()
    }

    fun markConversationCompleted(id: String) {
        viewModelScope.launch {
            repository.markConversationCompleted(id)
            _selectedConversation.value = _selectedConversation.value?.copy(isCompleted = true)
        }
    }

    // Admin dashboard features
    fun addCustomLesson(
        titleEn: String,
        titleHi: String,
        level: String,
        moduleType: String,
        conceptHi: String,
        formula: String,
        rules: String,
        examples: String
    ) {
        viewModelScope.launch {
            val newLesson = LessonEntity(
                id = "custom_${System.currentTimeMillis()}",
                level = level,
                moduleType = moduleType,
                titleEn = titleEn,
                titleHi = titleHi,
                summaryHi = conceptHi.take(60),
                conceptHi = conceptHi,
                formula = formula,
                rulesJson = rules,
                examplesJson = examples,
                orderIndex = 99
            )
            repository.insertLesson(newLesson)
        }
    }

    fun deleteLesson(id: String) {
        viewModelScope.launch {
            repository.deleteLesson(id)
        }
    }

    fun addCustomVocab(
        word: String,
        partOfSpeech: String,
        meaningHi: String,
        pronunciationHi: String,
        exampleEn: String,
        exampleHi: String,
        category: String
    ) {
        viewModelScope.launch {
            val vocab = VocabularyEntity(
                word = word,
                partOfSpeech = partOfSpeech,
                meaningHi = meaningHi,
                pronunciationHi = pronunciationHi,
                exampleEn = exampleEn,
                exampleHi = exampleHi,
                category = category
            )
            repository.insertVocab(vocab)
        }
    }

    fun deleteVocab(id: Int) {
        viewModelScope.launch {
            repository.deleteVocab(id)
        }
    }

    fun addCustomQuiz(
        category: String,
        level: String,
        questionEn: String,
        questionHi: String,
        options: String,
        correctIndex: Int,
        explanationHi: String
    ) {
        viewModelScope.launch {
            val quiz = QuizQuestionEntity(
                category = category,
                level = level,
                questionEn = questionEn,
                questionHi = questionHi,
                optionsJson = options,
                correctIndex = correctIndex,
                explanationHi = explanationHi
            )
            repository.insertQuiz(quiz)
        }
    }

    fun deleteQuiz(id: Int) {
        viewModelScope.launch {
            repository.deleteQuiz(id)
        }
    }

    fun updateProfileName(name: String, goal: String) {
        val current = userProfile.value ?: return
        viewModelScope.launch {
            repository.updateUserProfile(
                current.copy(name = name, targetGoal = goal)
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsManager.shutdown()
        speechManager.destroy()
    }
}

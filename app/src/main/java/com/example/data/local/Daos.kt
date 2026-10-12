package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProfile(profile: UserProfileEntity)

    @Query("UPDATE user_profile SET xpPoints = xpPoints + :points WHERE id = 1")
    suspend fun addXp(points: Int)

    @Query("UPDATE user_profile SET currentStreak = :streak WHERE id = 1")
    suspend fun updateStreak(streak: Int)

    @Query("UPDATE user_profile SET minutesSpentToday = :minutes WHERE id = 1")
    suspend fun updateMinutesSpent(minutes: Int)
}

@Dao
interface LessonDao {
    @Query("SELECT * FROM lessons ORDER BY orderIndex ASC")
    fun getAllLessons(): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons WHERE moduleType = :moduleType ORDER BY orderIndex ASC")
    fun getLessonsByModule(moduleType: String): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons WHERE level = :level AND moduleType = :moduleType ORDER BY orderIndex ASC")
    fun getLessonsByLevel(level: String, moduleType: String): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons WHERE id = :id LIMIT 1")
    suspend fun getLessonById(id: String): LessonEntity?

    @Query("UPDATE lessons SET isCompleted = :completed WHERE id = :id")
    suspend fun setLessonCompleted(id: String, completed: Boolean)

    @Query("UPDATE lessons SET isBookmarked = NOT isBookmarked WHERE id = :id")
    suspend fun toggleBookmark(id: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lessons: List<LessonEntity>)

    @Query("DELETE FROM lessons WHERE id = :id")
    suspend fun deleteLesson(id: String)

    @Query("SELECT COUNT(*) FROM lessons WHERE isCompleted = 1")
    fun getCompletedLessonCount(): Flow<Int>
}

@Dao
interface VocabDao {
    @Query("SELECT * FROM vocabulary ORDER BY id ASC")
    fun getAllVocab(): Flow<List<VocabularyEntity>>

    @Query("SELECT * FROM vocabulary WHERE category = :category ORDER BY id ASC")
    fun getVocabByCategory(category: String): Flow<List<VocabularyEntity>>

    @Query("SELECT * FROM vocabulary WHERE isFavorite = 1")
    fun getFavoriteVocab(): Flow<List<VocabularyEntity>>

    @Query("UPDATE vocabulary SET isFavorite = NOT isFavorite WHERE id = :id")
    suspend fun toggleFavorite(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVocab(vocab: VocabularyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vocabList: List<VocabularyEntity>)

    @Query("DELETE FROM vocabulary WHERE id = :id")
    suspend fun deleteVocab(id: Int)
}

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz_questions ORDER BY id ASC")
    fun getAllQuizzes(): Flow<List<QuizQuestionEntity>>

    @Query("SELECT * FROM quiz_questions WHERE category = :category ORDER BY id ASC")
    fun getQuizzesByCategory(category: String): Flow<List<QuizQuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: QuizQuestionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quizzes: List<QuizQuestionEntity>)

    @Query("DELETE FROM quiz_questions WHERE id = :id")
    suspend fun deleteQuiz(id: Int)
}

@Dao
interface ConversationDao {
    @Query("SELECT * FROM conversations ORDER BY id ASC")
    fun getAllConversations(): Flow<List<ConversationEntity>>

    @Query("SELECT * FROM conversations WHERE category = :category ORDER BY id ASC")
    fun getConversationsByCategory(category: String): Flow<List<ConversationEntity>>

    @Query("UPDATE conversations SET isCompleted = :completed WHERE id = :id")
    suspend fun setConversationCompleted(id: String, completed: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(conversations: List<ConversationEntity>)
}

@Dao
interface TranslationDao {
    @Query("SELECT * FROM saved_translations ORDER BY timestamp DESC")
    fun getAllSavedTranslations(): Flow<List<SavedTranslationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTranslation(translation: SavedTranslationEntity)

    @Query("DELETE FROM saved_translations WHERE id = :id")
    suspend fun deleteTranslation(id: Int)
}

@Dao
interface TeacherChatDao {
    @Query("SELECT * FROM teacher_messages ORDER BY timestamp ASC")
    fun getAllMessages(): Flow<List<TeacherMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: TeacherMessageEntity)

    @Query("DELETE FROM teacher_messages")
    suspend fun clearChat()
}

@Dao
interface BadgeDao {
    @Query("SELECT * FROM badges")
    fun getAllBadges(): Flow<List<BadgeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(badges: List<BadgeEntity>)

    @Query("UPDATE badges SET isUnlocked = :unlocked, progress = :progress WHERE id = :id")
    suspend fun updateBadge(id: String, unlocked: Boolean, progress: Float)
}

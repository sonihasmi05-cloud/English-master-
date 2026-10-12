package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserProfileEntity::class,
        LessonEntity::class,
        VocabularyEntity::class,
        QuizQuestionEntity::class,
        ConversationEntity::class,
        SavedTranslationEntity::class,
        TeacherMessageEntity::class,
        BadgeEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class EnglishMasterDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun lessonDao(): LessonDao
    abstract fun vocabDao(): VocabDao
    abstract fun quizDao(): QuizDao
    abstract fun conversationDao(): ConversationDao
    abstract fun translationDao(): TranslationDao
    abstract fun teacherChatDao(): TeacherChatDao
    abstract fun badgeDao(): BadgeDao

    companion object {
        @Volatile
        private var INSTANCE: EnglishMasterDatabase? = null

        fun getDatabase(context: Context): EnglishMasterDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EnglishMasterDatabase::class.java,
                    "english_master_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

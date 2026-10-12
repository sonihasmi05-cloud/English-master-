package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val name: String = "Rahul Sharma",
    val email: String = "rahul.learner@gmail.com",
    val targetGoal: String = "Fluent in 60 Days",
    val dailyGoalMinutes: Int = 15,
    val minutesSpentToday: Int = 10,
    val currentStreak: Int = 5,
    val highestStreak: Int = 14,
    val xpPoints: Int = 680,
    val levelName: String = "Intermediate Learner",
    val isSignedIn: Boolean = true
)

@Entity(tableName = "lessons")
data class LessonEntity(
    @PrimaryKey val id: String,
    val level: String, // "Beginner", "Basic", "Intermediate", "Advanced"
    val moduleType: String, // "COURSE" or "GRAMMAR"
    val titleEn: String,
    val titleHi: String,
    val summaryHi: String,
    val conceptHi: String,
    val formula: String,
    val rulesJson: String, // delimiter separated or json
    val examplesJson: String, // delimiter separated
    val isCompleted: Boolean = false,
    val isBookmarked: Boolean = false,
    val orderIndex: Int = 0
)

@Entity(tableName = "vocabulary")
data class VocabularyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val partOfSpeech: String, // "Noun", "Verb", "Adjective", etc.
    val meaningHi: String,
    val pronunciationHi: String,
    val exampleEn: String,
    val exampleHi: String,
    val category: String, // "Daily 10", "Business", "Emotions", "Travel", "Kitchen", "Daily Routine"
    val isFavorite: Boolean = false,
    val dayNumber: Int = 1
)

@Entity(tableName = "quiz_questions")
data class QuizQuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String, // "Daily", "Grammar", "Vocabulary", "Speaking"
    val level: String, // "Beginner", "Intermediate", "Advanced"
    val questionEn: String,
    val questionHi: String,
    val optionsJson: String, // "A||B||C||D"
    val correctIndex: Int,
    val explanationHi: String
)

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey val id: String,
    val category: String, // "School", "Shopping", "Family", "Interview", "Office", "Travel"
    val titleEn: String,
    val titleHi: String,
    val situationHi: String,
    val turnsJson: String, // serializable or structured string
    val isCompleted: Boolean = false
)

@Entity(tableName = "saved_translations")
data class SavedTranslationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val hindiText: String,
    val englishText: String,
    val explanationHi: String,
    val pronunciationHi: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "teacher_messages")
data class TeacherMessageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sender: String, // "USER" or "TEACHER"
    val messageText: String,
    val hindiExplanation: String = "",
    val correctedVersion: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "badges")
data class BadgeEntity(
    @PrimaryKey val id: String,
    val titleEn: String,
    val titleHi: String,
    val descriptionHi: String,
    val iconType: String,
    val isUnlocked: Boolean = false,
    val progress: Float = 0f // 0f to 1.0f
)

package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.GTranslate
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AppHeader
import com.example.ui.components.AudioSpeakerButton
import com.example.ui.components.MetricProgressCard
import com.example.ui.components.SectionHeading
import com.example.ui.theme.FlameGold
import com.example.ui.theme.LightBlueSecondary
import com.example.ui.theme.LightBlueSecondaryContainer
import com.example.ui.theme.RoyalBlueContainer
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.RoyalBluePrimary
import com.example.ui.theme.RoyalBlueVariant
import com.example.ui.viewmodel.EnglishMasterViewModel
import com.example.ui.viewmodel.Screen

data class DashboardModuleItem(
    val titleEn: String,
    val titleHi: String,
    val icon: ImageVector,
    val iconBgColor: Color,
    val screen: Screen,
    val badgeText: String? = null
)

@Composable
fun HomeScreen(
    viewModel: EnglishMasterViewModel,
    modifier: Modifier = Modifier
) {
    val userProfile by viewModel.userProfile.collectAsState()
    val completedCount by viewModel.completedLessonCount.collectAsState()
    val vocabList by viewModel.allVocab.collectAsState()
    val courseLessons by viewModel.courseLessons.collectAsState()

    val userName = userProfile?.name ?: "Learner"
    val streak = userProfile?.currentStreak ?: 5
    val xp = userProfile?.xpPoints ?: 850
    val targetMinutes = userProfile?.dailyGoalMinutes ?: 20
    val minutesSpent = userProfile?.minutesSpentToday ?: 12
    val dailyProgress = (minutesSpent.toFloat() / targetMinutes.toFloat()).coerceIn(0f, 1f)

    val wordOfTheDay = vocabList.firstOrNull()

    val modules = listOf(
        DashboardModuleItem("English Course", "सम्पूर्ण कोर्स (A1-C1)", Icons.Default.School, RoyalBluePrimary, Screen.COURSES, "4 Levels"),
        DashboardModuleItem("Daily Vocabulary", "रोज़ाना 10 नए शब्द", Icons.Default.Book, Color(0xFF0284C7), Screen.VOCABULARY, "Audio"),
        DashboardModuleItem("Speaking Partner", "बोलने का अभ्यास & AI", Icons.Default.Mic, Color(0xFF7C3AED), Screen.SPEAKING, "Voice"),
        DashboardModuleItem("Full Grammar", "संज्ञा से काल (Tenses)", Icons.Default.Lightbulb, Color(0xFF059669), Screen.GRAMMAR, "9 Units"),
        DashboardModuleItem("Hindi Translator", "हिन्दी से अंग्रेजी अनुवाद", Icons.Default.GTranslate, Color(0xFFD97706), Screen.TRANSLATOR, "Smart"),
        DashboardModuleItem("Daily Dialogue", "दुकान, स्कूल, ऑफिस वार्ता", Icons.Default.Chat, Color(0xFF2563EB), Screen.CONVERSATIONS, "Roleplay"),
        DashboardModuleItem("Quiz & Tests", "डेली MCQ & लीडरबोर्ड", Icons.Default.Quiz, Color(0xFFE11D48), Screen.QUIZ, "Daily"),
        DashboardModuleItem("AI Guru Teacher", "24/7 व्यक्तिगत शिक्षक", Icons.Default.SmartToy, Color(0xFF4F46E5), Screen.AI_TEACHER, "24/7 AI")
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        AppHeader(
            title = "ENGLISH MASTER",
            subtitle = "नमस्ते, $userName! आज कुछ नया सीखें",
            streakCount = streak,
            xpPoints = xp,
            onStreakClick = { viewModel.navigateTo(Screen.PROFILE) },
            onProfileClick = { viewModel.navigateTo(Screen.PROFILE) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            // Welcome & Motivational Card
            item {
                Spacer(modifier = Modifier.height(14.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = RoyalBlueDark),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(
                                    listOf(RoyalBlueDark, RoyalBluePrimary)
                                )
                            )
                            .padding(18.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.LocalFireDepartment,
                                        contentDescription = "Streak",
                                        tint = FlameGold,
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "$streak Day Streak Active!",
                                        fontWeight = FontWeight.Bold,
                                        color = FlameGold,
                                        fontSize = 14.sp
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color.White.copy(alpha = 0.2f))
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = "${userProfile?.levelName ?: "Beginner"}",
                                        color = Color.White,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Learn with Confidence • बिना झिझक अंग्रेजी बोलें",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "रोज़ केवल 15 मिनट अभ्यास करें और 60 दिनों में आत्मविश्वास से बात करें।",
                                color = Color.White.copy(alpha = 0.85f),
                                fontSize = 12.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            // Continue learning button
                            Button(
                                onClick = {
                                    val nextLesson = courseLessons.firstOrNull { !it.isCompleted } ?: courseLessons.firstOrNull()
                                    if (nextLesson != null) {
                                        viewModel.openLesson(nextLesson)
                                    } else {
                                        viewModel.navigateTo(Screen.COURSES)
                                    }
                                },
                                modifier = Modifier
                                    .testTag("continue_learning_button")
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = FlameGold,
                                    contentColor = RoyalBlueDark
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = "Play", modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Continue Learning (पढ़ाई जारी रखें)", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }

            // Daily Target Progress
            item {
                Spacer(modifier = Modifier.height(14.dp))
                MetricProgressCard(
                    title = "Daily Target: $minutesSpent / $targetMinutes mins",
                    subtitle = "आज का लक्ष्य: 20 मिनट अभ्यास",
                    progress = dailyProgress,
                    targetText = "आप $completedCount पाठ पूरे कर चुके हैं! लक्ष्य पूरा करने के लिए 8 मिनट और पढ़ें।"
                )
            }

            // Word of the Day Highlight
            if (wordOfTheDay != null) {
                item {
                    Spacer(modifier = Modifier.height(14.dp))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = LightBlueSecondaryContainer),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(RoyalBluePrimary)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text("Word of the Day", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = wordOfTheDay.partOfSpeech,
                                        color = Color(0xFF475569),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                AudioSpeakerButton(
                                    onClick = { viewModel.speak(wordOfTheDay.word) }
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = wordOfTheDay.word,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = RoyalBlueDark
                                )
                            )
                            Text(
                                text = "उच्चारण: ${wordOfTheDay.pronunciationHi} • अर्थ: ${wordOfTheDay.meaningHi}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = RoyalBluePrimary,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Example: \"${wordOfTheDay.exampleEn}\"",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFF1E293B),
                                    fontWeight = FontWeight.Normal
                                )
                            )
                            Text(
                                text = "हिन्दी: ${wordOfTheDay.exampleHi}",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFF64748B),
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }
            }

            // All Learning Modules Section Heading
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeading(
                    titleEn = "Learning Modules",
                    titleHi = "सभी मुख्य अभ्यास अनुभाग",
                    actionText = "Explore All",
                    onActionClick = { viewModel.navigateTo(Screen.COURSES) }
                )
            }

            // Grid of Modules
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    for (rowItems in modules.chunked(2)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            for (module in rowItems) {
                                Card(
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("module_card_${module.screen.name.lowercase()}")
                                        .clickable { viewModel.navigateTo(module.screen) },
                                    shape = RoundedCornerShape(14.dp),
                                    colors = CardDefaults.cardColors(containerColor = Color.White),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(14.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(40.dp)
                                                    .clip(RoundedCornerShape(10.dp))
                                                    .background(module.iconBgColor.copy(alpha = 0.15f)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Icon(
                                                    imageVector = module.icon,
                                                    contentDescription = module.titleEn,
                                                    tint = module.iconBgColor,
                                                    modifier = Modifier.size(22.dp)
                                                )
                                            }

                                            if (module.badgeText != null) {
                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(8.dp))
                                                        .background(RoyalBlueContainer)
                                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                                ) {
                                                    Text(
                                                        text = module.badgeText,
                                                        color = RoyalBluePrimary,
                                                        fontSize = 10.sp,
                                                        fontWeight = FontWeight.Bold
                                                    )
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        Text(
                                            text = module.titleEn,
                                            fontWeight = FontWeight.Bold,
                                            color = RoyalBlueDark,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = module.titleHi,
                                            color = Color(0xFF64748B),
                                            fontSize = 11.sp,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Quick AI Teacher Prompt Banner
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clickable { viewModel.navigateTo(Screen.AI_TEACHER) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = RoyalBlueContainer),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(RoyalBluePrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SmartToy,
                                contentDescription = "AI Tutor",
                                tint = Color.White,
                                modifier = Modifier.size(26.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Have a Doubt? Ask Guru AI 24/7",
                                fontWeight = FontWeight.Bold,
                                color = RoyalBlueDark,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "कोई भी वाक्य लिखें या बोलें, AI शिक्षक तुरंत हिन्दी में समझाएगा।",
                                color = Color(0xFF334155),
                                fontSize = 11.sp
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Go",
                            tint = RoyalBluePrimary
                        )
                    }
                }
            }
        }
    }
}

package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.seed.SeedData
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.FlameGold
import com.example.ui.theme.LightBlueSecondaryContainer
import com.example.ui.theme.RoyalBlueContainer
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.RoyalBluePrimary
import com.example.ui.theme.RoyalBlueVariant
import com.example.ui.theme.SuccessGreen
import com.example.ui.viewmodel.EnglishMasterViewModel

@Composable
fun QuizScreen(
    viewModel: EnglishMasterViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val allQuizzes by viewModel.allQuizzes.collectAsState()
    val activeCategory by viewModel.activeQuizCategory.collectAsState()
    val currentIndex by viewModel.quizCurrentIndex.collectAsState()
    val selectedOption by viewModel.quizSelectedOption.collectAsState()
    val quizScore by viewModel.quizScore.collectAsState()
    val quizFinished by viewModel.quizFinished.collectAsState()

    var showLeaderboard by remember { mutableStateOf(false) }

    val categories = listOf("Daily", "Grammar", "Vocabulary", "Speaking")

    val currentQuizzes = allQuizzes.filter { it.category.equals(activeCategory, ignoreCase = true) }
    val currentQuestion = currentQuizzes.getOrNull(currentIndex)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = RoyalBluePrimary,
            shadowElevation = 3.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { viewModel.navigateBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Column {
                        Text(
                            text = "English Master Quiz System",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Text(
                            text = "डेली टेस्ट • स्कोर & लीडरबोर्ड",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White.copy(alpha = 0.85f),
                                fontSize = 11.sp
                            )
                        )
                    }
                }

                IconButton(onClick = { showLeaderboard = !showLeaderboard }) {
                    Icon(
                        imageVector = Icons.Default.Leaderboard,
                        contentDescription = "Leaderboard",
                        tint = if (showLeaderboard) FlameGold else Color.White
                    )
                }
            }
        }

        if (showLeaderboard) {
            // Leaderboard View
            LeaderboardView(onClose = { showLeaderboard = false })
        } else {
            // Category Chips Row
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { cat ->
                    val isSelected = activeCategory == cat
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.setQuizCategory(cat) },
                        label = {
                            Text(
                                text = when (cat) {
                                    "Daily" -> "Daily MCQ"
                                    "Grammar" -> "Grammar Test"
                                    "Vocabulary" -> "Vocab Test"
                                    "Speaking" -> "Speaking Challenge"
                                    else -> cat
                                },
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = RoyalBluePrimary,
                            selectedLabelColor = Color.White,
                            containerColor = Color.White,
                            labelColor = RoyalBlueDark
                        )
                    )
                }
            }

            if (quizFinished) {
                // Quiz Completed Summary Screen
                QuizResultSummary(
                    score = quizScore,
                    totalQuestions = currentQuizzes.size,
                    onRestart = { viewModel.resetQuiz() },
                    onLeaderboard = { showLeaderboard = true }
                )
            } else if (currentQuestion == null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No questions available in this category.", color = Color(0xFF64748B))
                }
            } else {
                // Active Question View
                val options = currentQuestion.optionsJson.split("||")
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    // Progress & Score indicator
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Question ${currentIndex + 1} of ${currentQuizzes.size}",
                                fontWeight = FontWeight.Bold,
                                color = RoyalBlueDark,
                                fontSize = 14.sp
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(FlameGold)
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "Score: $quizScore pts",
                                    fontWeight = FontWeight.Bold,
                                    color = RoyalBlueDark,
                                    fontSize = 12.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    // Question Card
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("quiz_question_card"),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = currentQuestion.questionEn,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = RoyalBlueDark,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = currentQuestion.questionHi,
                                    color = Color(0xFF64748B),
                                    fontSize = 13.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                    }

                    // Options List
                    items(options.indices.toList()) { index ->
                        val optText = options[index]
                        val isSelected = selectedOption == index
                        val isAnswered = selectedOption != null
                        val isCorrect = index == currentQuestion.correctIndex

                        val borderCol = when {
                            !isAnswered -> Color(0xFFE2E8F0)
                            isCorrect -> SuccessGreen
                            isSelected -> ErrorRed
                            else -> Color(0xFFE2E8F0)
                        }

                        val containerCol = when {
                            !isAnswered -> if (isSelected) RoyalBlueContainer else Color.White
                            isCorrect -> SuccessGreen.copy(alpha = 0.12f)
                            isSelected -> ErrorRed.copy(alpha = 0.12f)
                            else -> Color.White
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("quiz_option_$index")
                                .padding(vertical = 5.dp)
                                .border(1.5.dp, borderCol, RoundedCornerShape(12.dp))
                                .clickable(enabled = !isAnswered) {
                                    viewModel.selectQuizOption(index)
                                    viewModel.submitQuizAnswer(currentQuestion.correctIndex)
                                },
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = containerCol),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(
                                            when {
                                                isAnswered && isCorrect -> SuccessGreen
                                                isAnswered && isSelected -> ErrorRed
                                                else -> RoyalBlueContainer
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = ('A'.code + index).toChar().toString(),
                                        fontWeight = FontWeight.Bold,
                                        color = if (isAnswered && (isCorrect || isSelected)) Color.White else RoyalBlueDark,
                                        fontSize = 12.sp
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    text = optText,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF1E293B),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    // Explanation & Next Button
                    if (selectedOption != null) {
                        item {
                            Spacer(modifier = Modifier.height(12.dp))
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selectedOption == currentQuestion.correctIndex)
                                        SuccessGreen.copy(alpha = 0.1f) else LightBlueSecondaryContainer
                                )
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = if (selectedOption == currentQuestion.correctIndex) "बधाई हो! सही उत्तर ✓" else "उत्तर व्याख्या (Explanation):",
                                        fontWeight = FontWeight.Bold,
                                        color = if (selectedOption == currentQuestion.correctIndex) SuccessGreen else RoyalBlueDark,
                                        fontSize = 13.sp
                                    )
                                    Text(
                                        text = currentQuestion.explanationHi,
                                        fontSize = 12.sp,
                                        color = Color(0xFF334155),
                                        modifier = Modifier.padding(top = 2.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Button(
                                onClick = { viewModel.nextQuizQuestion(currentQuizzes.size) },
                                modifier = Modifier
                                    .testTag("quiz_next_button")
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = RoyalBluePrimary)
                            ) {
                                Text(
                                    text = if (currentIndex + 1 < currentQuizzes.size) "Next Question (अगला सवाल)" else "Finish Quiz (स्कोर देखें)",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizResultSummary(
    score: Int,
    totalQuestions: Int,
    onRestart: () -> Unit,
    onLeaderboard: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(FlameGold.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = "Trophy",
                        tint = FlameGold,
                        modifier = Modifier.size(44.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Quiz Completed! (क्विज़ समाप्त)",
                    fontWeight = FontWeight.ExtraBold,
                    color = RoyalBlueDark,
                    fontSize = 18.sp
                )

                Text(
                    text = "You earned $score Points!",
                    fontWeight = FontWeight.Bold,
                    color = RoyalBluePrimary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Text(
                    text = "शानदार प्रयास! आपकी अंग्रेजी में सुधार हो रहा है। रोज़ाना क्विज़ खेलकर लीडरबोर्ड पर शीर्ष पर पहुँचें।",
                    color = Color(0xFF64748B),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onRestart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBluePrimary)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Refresh, contentDescription = "Retry", modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Play Again (दोबारा खेलें)", fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = onLeaderboard,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightBlueSecondaryContainer, contentColor = RoyalBlueDark)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Leaderboard, contentDescription = "Leaderboard", modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("View Leaderboard (रैंक देखें)", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
fun LeaderboardView(onClose: () -> Unit) {
    val users = SeedData.sampleLeaderboard

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "All-India Learners Leaderboard",
                fontWeight = FontWeight.ExtraBold,
                color = RoyalBlueDark,
                fontSize = 16.sp
            )
            Button(
                onClick = onClose,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = RoyalBlueContainer, contentColor = RoyalBlueDark)
            ) {
                Text("Back to Quiz", fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(users) { u ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (u.isCurrentUser) RoyalBlueContainer else Color.White
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(
                                    when (u.rank) {
                                        1 -> FlameGold
                                        2 -> Color(0xFF94A3B8)
                                        3 -> Color(0xFFB45309)
                                        else -> Color(0xFFE2E8F0)
                                    }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "#${u.rank}",
                                fontWeight = FontWeight.Bold,
                                color = if (u.rank <= 3) Color.White else Color(0xFF475569),
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = u.name,
                                fontWeight = FontWeight.Bold,
                                color = RoyalBlueDark,
                                fontSize = 14.sp
                            )
                            Text(
                                text = u.city,
                                color = Color(0xFF64748B),
                                fontSize = 11.sp
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocalFireDepartment, contentDescription = "Streak", tint = FlameGold, modifier = Modifier.size(16.dp))
                            Text(
                                text = "${u.streak}d",
                                fontWeight = FontWeight.Bold,
                                color = FlameGold,
                                fontSize = 11.sp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "${u.points} XP",
                                fontWeight = FontWeight.ExtraBold,
                                color = RoyalBluePrimary,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

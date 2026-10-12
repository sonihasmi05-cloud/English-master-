package com.example.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.FlameGold
import com.example.ui.theme.RoyalBlueContainer
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.RoyalBluePrimary
import com.example.ui.theme.RoyalBlueVariant
import com.example.ui.theme.SuccessGreen
import com.example.ui.viewmodel.EnglishMasterViewModel

@Composable
fun AdminScreen(
    viewModel: EnglishMasterViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val context = LocalContext.current
    val allLessons by viewModel.allLessons.collectAsState()
    val allVocab by viewModel.allVocab.collectAsState()
    val allQuizzes by viewModel.allQuizzes.collectAsState()
    val userProfile by viewModel.userProfile.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Overview", "Add Lesson", "Add Vocab", "Add Quiz")

    // Form states
    var lessonTitleEn by remember { mutableStateOf("") }
    var lessonTitleHi by remember { mutableStateOf("") }
    var lessonLevel by remember { mutableStateOf("Beginner") }
    var lessonConceptHi by remember { mutableStateOf("") }
    var lessonFormula by remember { mutableStateOf("Subject + Verb + Object") }

    var vocabWord by remember { mutableStateOf("") }
    var vocabMeaningHi by remember { mutableStateOf("") }
    var vocabPronunciationHi by remember { mutableStateOf("") }
    var vocabExampleEn by remember { mutableStateOf("") }
    var vocabExampleHi by remember { mutableStateOf("") }
    var vocabCategory by remember { mutableStateOf("Daily 10") }

    var quizQuestionEn by remember { mutableStateOf("") }
    var quizQuestionHi by remember { mutableStateOf("") }
    var quizOptions by remember { mutableStateOf("Option A||Option B||Option C||Option D") }
    var quizCorrectIndex by remember { mutableStateOf("0") }
    var quizExplanationHi by remember { mutableStateOf("") }

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
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.navigateBack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Admin Dashboard",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.Default.AdminPanelSettings, contentDescription = "Admin", tint = FlameGold, modifier = Modifier.size(18.dp))
                    }
                    Text(
                        text = "पाठ्यक्रम, शब्दावली, क्विज़ व आंकड़े प्रबंधन",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 11.sp
                        )
                    )
                }
            }
        }

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            contentColor = RoyalBluePrimary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            when (selectedTabIndex) {
                0 -> {
                    // Overview Stats
                    item {
                        Text(
                            text = "Platform Statistics (प्लेटफ़ॉर्म आंकड़े)",
                            fontWeight = FontWeight.Bold,
                            color = RoyalBlueDark,
                            fontSize = 15.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            StatCard(
                                title = "Total Lessons",
                                value = "${allLessons.size}",
                                icon = Icons.Default.School,
                                color = RoyalBluePrimary,
                                modifier = Modifier.weight(1f)
                            )
                            StatCard(
                                title = "Vocabulary",
                                value = "${allVocab.size} Words",
                                icon = Icons.Default.MenuBook,
                                color = Color(0xFF0284C7),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            StatCard(
                                title = "Quiz Bank",
                                value = "${allQuizzes.size} MCQs",
                                icon = Icons.Default.Quiz,
                                color = Color(0xFF7C3AED),
                                modifier = Modifier.weight(1f)
                            )
                            StatCard(
                                title = "Active Learners",
                                value = "1,420+",
                                icon = Icons.Default.People,
                                color = SuccessGreen,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Current Registered User:",
                            fontWeight = FontWeight.Bold,
                            color = RoyalBlueDark,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = "Name: ${userProfile?.name ?: "Student"}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = RoyalBlueDark
                                )
                                Text(
                                    text = "Email: ${userProfile?.email ?: "student@gmail.com"}",
                                    color = Color(0xFF64748B),
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = "Total XP: ${userProfile?.xpPoints ?: 0} pts • Streak: ${userProfile?.currentStreak ?: 0} days",
                                    fontWeight = FontWeight.SemiBold,
                                    color = RoyalBlueVariant,
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }

                1 -> {
                    // Add Custom Lesson Form
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Add New Lesson (नया पाठ जोड़ें)", fontWeight = FontWeight.Bold, color = RoyalBlueDark, fontSize = 15.sp)
                                Spacer(modifier = Modifier.height(10.dp))

                                OutlinedTextField(
                                    value = lessonTitleEn,
                                    onValueChange = { lessonTitleEn = it },
                                    label = { Text("Lesson Title (English)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = lessonTitleHi,
                                    onValueChange = { lessonTitleHi = it },
                                    label = { Text("Lesson Title (Hindi)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = lessonLevel,
                                    onValueChange = { lessonLevel = it },
                                    label = { Text("Level (Beginner, Basic, Intermediate, Advanced)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = lessonConceptHi,
                                    onValueChange = { lessonConceptHi = it },
                                    label = { Text("Concept in Hindi (हिन्दी व्याख्या)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = lessonFormula,
                                    onValueChange = { lessonFormula = it },
                                    label = { Text("Formula / Rule") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )

                                Spacer(modifier = Modifier.height(14.dp))
                                Button(
                                    onClick = {
                                        if (lessonTitleEn.isNotBlank() && lessonTitleHi.isNotBlank()) {
                                            viewModel.addCustomLesson(
                                                titleEn = lessonTitleEn,
                                                titleHi = lessonTitleHi,
                                                level = lessonLevel,
                                                moduleType = "COURSE",
                                                conceptHi = lessonConceptHi,
                                                formula = lessonFormula,
                                                rules = "Always speak with clarity||Practice daily",
                                                examples = "I am ready.::मैं तैयार हूँ।::आई एम रेडी।"
                                            )
                                            Toast.makeText(context, "Lesson Added Successfully!", Toast.LENGTH_SHORT).show()
                                            lessonTitleEn = ""
                                            lessonTitleHi = ""
                                            lessonConceptHi = ""
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBluePrimary)
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Add")
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Publish Lesson (पाठ जोड़ें)", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                2 -> {
                    // Add Custom Vocab Form
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Add Daily Vocabulary (नया शब्द जोड़ें)", fontWeight = FontWeight.Bold, color = RoyalBlueDark, fontSize = 15.sp)
                                Spacer(modifier = Modifier.height(10.dp))

                                OutlinedTextField(
                                    value = vocabWord,
                                    onValueChange = { vocabWord = it },
                                    label = { Text("English Word (जैसे: Enthusiastic)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = vocabMeaningHi,
                                    onValueChange = { vocabMeaningHi = it },
                                    label = { Text("Hindi Meaning (अर्थ - उत्साही)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = vocabPronunciationHi,
                                    onValueChange = { vocabPronunciationHi = it },
                                    label = { Text("Pronunciation in Hindi (उच्चारण - एंथूज़ियास्टिक)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = vocabExampleEn,
                                    onValueChange = { vocabExampleEn = it },
                                    label = { Text("Example Sentence (English)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = vocabExampleHi,
                                    onValueChange = { vocabExampleHi = it },
                                    label = { Text("Example Sentence (Hindi)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )

                                Spacer(modifier = Modifier.height(14.dp))
                                Button(
                                    onClick = {
                                        if (vocabWord.isNotBlank() && vocabMeaningHi.isNotBlank()) {
                                            viewModel.addCustomVocab(
                                                word = vocabWord,
                                                partOfSpeech = "Adjective",
                                                meaningHi = vocabMeaningHi,
                                                pronunciationHi = vocabPronunciationHi.ifBlank { vocabWord },
                                                exampleEn = vocabExampleEn.ifBlank { "He is very $vocabWord." },
                                                exampleHi = vocabExampleHi.ifBlank { "वह बहुत $vocabMeaningHi है।" },
                                                category = vocabCategory
                                            )
                                            Toast.makeText(context, "Vocabulary Word Added!", Toast.LENGTH_SHORT).show()
                                            vocabWord = ""
                                            vocabMeaningHi = ""
                                            vocabPronunciationHi = ""
                                            vocabExampleEn = ""
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBluePrimary)
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Add")
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Add Vocabulary Word (शब्द जोड़ें)", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                3 -> {
                    // Add Custom Quiz Form
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Add Quiz Question (क्विज़ प्रश्न जोड़ें)", fontWeight = FontWeight.Bold, color = RoyalBlueDark, fontSize = 15.sp)
                                Spacer(modifier = Modifier.height(10.dp))

                                OutlinedTextField(
                                    value = quizQuestionEn,
                                    onValueChange = { quizQuestionEn = it },
                                    label = { Text("Question in English") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = quizQuestionHi,
                                    onValueChange = { quizQuestionHi = it },
                                    label = { Text("Question in Hindi") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = quizOptions,
                                    onValueChange = { quizOptions = it },
                                    label = { Text("4 Options separated by '||' (e.g. go||goes||went||gone)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = quizCorrectIndex,
                                    onValueChange = { quizCorrectIndex = it },
                                    label = { Text("Correct Index (0 for A, 1 for B, 2 for C, 3 for D)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = quizExplanationHi,
                                    onValueChange = { quizExplanationHi = it },
                                    label = { Text("Explanation in Hindi (व्याकरण कारण)") },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp)
                                )

                                Spacer(modifier = Modifier.height(14.dp))
                                Button(
                                    onClick = {
                                        if (quizQuestionEn.isNotBlank()) {
                                            val cIdx = quizCorrectIndex.toIntOrNull() ?: 0
                                            viewModel.addCustomQuiz(
                                                category = "Daily",
                                                level = "Basic",
                                                questionEn = quizQuestionEn,
                                                questionHi = quizQuestionHi,
                                                options = quizOptions,
                                                correctIndex = cIdx.coerceIn(0, 3),
                                                explanationHi = quizExplanationHi.ifBlank { "यह सही व्याकरण नियम है।" }
                                            )
                                            Toast.makeText(context, "Quiz Question Added!", Toast.LENGTH_SHORT).show()
                                            quizQuestionEn = ""
                                            quizQuestionHi = ""
                                            quizExplanationHi = ""
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = RoyalBluePrimary)
                                ) {
                                    Icon(Icons.Default.Add, contentDescription = "Add")
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Add Quiz Question (क्विज़ जोड़ें)", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = title, tint = color, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, fontWeight = FontWeight.ExtraBold, color = RoyalBlueDark, fontSize = 16.sp)
            Text(text = title, color = Color(0xFF64748B), fontSize = 11.sp)
        }
    }
}

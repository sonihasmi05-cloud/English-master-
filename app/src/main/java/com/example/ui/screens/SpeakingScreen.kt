package com.example.ui.screens

import android.Manifest
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicNone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Stop
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AudioSpeakerButton
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
fun SpeakingScreen(
    viewModel: EnglishMasterViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val isListening by viewModel.speechManager.isListening.collectAsState()
    val recognizedText by viewModel.speechManager.recognizedText.collectAsState()
    val speechError by viewModel.speechManager.errorMessage.collectAsState()
    val analysisResult by viewModel.speechAnalysis.collectAsState()

    var manualTextInput by remember { mutableStateOf("") }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.speechManager.startListening()
        }
    }

    val sampleSentences = listOf(
        "Did you went to market?",
        "She don't know my name.",
        "I am having two brothers.",
        "Myself Rohan from Delhi.",
        "I am agree with your decision.",
        "He is more taller than me.",
        "I didn't saw him yesterday."
    )

    val infiniteTransition = rememberInfiniteTransition(label = "mic_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (isListening) 1.22f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

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
                    Text(
                        text = "Speaking Practice & AI Partner",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(
                        text = "माइक में बोलें • AI व्याकरण जाँचेगा और हिन्दी में समझाएगा",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 11.sp
                        )
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            // Interactive Mic Partner Area
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (isListening) "Listening... Speak in English now!" else "Tap the Mic and Speak English",
                            fontWeight = FontWeight.Bold,
                            color = if (isListening) RoyalBlueVariant else RoyalBlueDark,
                            fontSize = 15.sp
                        )
                        Text(
                            text = if (isListening) "आवाज़ रिकॉर्ड हो रही है..." else "माइक दबाकर अंग्रेजी वाक्य बोलें",
                            color = Color(0xFF64748B),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 2.dp)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // Mic Button with Animated Pulse
                        Box(
                            modifier = Modifier
                                .testTag("speaking_mic_button")
                                .size(84.dp)
                                .scale(pulseScale)
                                .clip(CircleShape)
                                .background(
                                    if (isListening) ErrorRed else RoyalBluePrimary
                                )
                                .clickable {
                                    if (isListening) {
                                        viewModel.speechManager.stopListening()
                                        if (recognizedText.isNotBlank()) {
                                            viewModel.analyzeSpeechText(recognizedText)
                                        }
                                    } else {
                                        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (isListening) Icons.Default.Stop else Icons.Default.Mic,
                                contentDescription = "Mic",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        if (speechError != null) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = speechError ?: "",
                                color = ErrorRed,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        if (recognizedText.isNotBlank()) {
                            Spacer(modifier = Modifier.height(14.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(LightBlueSecondaryContainer)
                                    .padding(12.dp)
                            ) {
                                Column {
                                    Text(
                                        text = "Captured Speech: \"$recognizedText\"",
                                        fontWeight = FontWeight.Bold,
                                        color = RoyalBlueDark,
                                        fontSize = 13.sp
                                    )
                                    Button(
                                        onClick = { viewModel.analyzeSpeechText(recognizedText) },
                                        modifier = Modifier
                                            .align(Alignment.End)
                                            .padding(top = 6.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = RoyalBluePrimary)
                                    ) {
                                        Text("Check My Speech (जाँचें)", fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Quick Test Sentences Chips (To simulate or test common grammar issues)
            item {
                Text(
                    text = "Quick Test Sentences (आम गलतियाँ चुनकर देखें):",
                    fontWeight = FontWeight.Bold,
                    color = RoyalBlueDark,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    items(sampleSentences) { sent ->
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(RoyalBlueContainer)
                                .clickable {
                                    manualTextInput = sent
                                    viewModel.analyzeSpeechText(sent)
                                }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = sent,
                                color = RoyalBlueDark,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
            }

            // Text Input Fallback
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Or Type Your Sentence (लिखकर जाँचें):",
                            fontWeight = FontWeight.Bold,
                            color = RoyalBlueDark,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            OutlinedTextField(
                                value = manualTextInput,
                                onValueChange = { manualTextInput = it },
                                modifier = Modifier
                                    .testTag("speaking_text_input")
                                    .weight(1f),
                                placeholder = { Text("e.g., Did you went there?", fontSize = 12.sp) },
                                shape = RoundedCornerShape(10.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = RoyalBluePrimary,
                                    unfocusedBorderColor = Color(0xFFCBD5E1)
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    if (manualTextInput.isNotBlank()) {
                                        viewModel.analyzeSpeechText(manualTextInput)
                                    }
                                },
                                modifier = Modifier.testTag("speaking_check_button"),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = RoyalBluePrimary)
                            ) {
                                Icon(Icons.Default.Send, contentDescription = "Check")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Speech & Grammar Analysis Results Card
            if (analysisResult != null) {
                val res = analysisResult!!
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("speech_analysis_result_card"),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // Header & Scores
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "AI Grammar Analysis (विश्लेषण)",
                                    fontWeight = FontWeight.ExtraBold,
                                    color = RoyalBlueDark,
                                    fontSize = 15.sp
                                )

                                Row {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(SuccessGreen.copy(alpha = 0.15f))
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = "Grammar: ${res.grammarScore}%",
                                            fontWeight = FontWeight.Bold,
                                            color = SuccessGreen,
                                            fontSize = 11.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(RoyalBlueContainer)
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = "Fluency: ${res.fluencyScore}%",
                                            fontWeight = FontWeight.Bold,
                                            color = RoyalBluePrimary,
                                            fontSize = 11.sp
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Original Sentence
                            Text(
                                text = "Your Sentence (आपका वाक्य):",
                                fontSize = 11.sp,
                                color = Color(0xFF64748B),
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "\"${res.userSpeech}\"",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (res.mistakes.isEmpty()) SuccessGreen else ErrorRed
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Corrected Sentence with Audio
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFFEFF6FF))
                                    .padding(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Correct English (शुद्ध वाक्य):",
                                            fontSize = 11.sp,
                                            color = RoyalBluePrimary,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = res.correctedEnglish,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = RoyalBlueDark
                                        )
                                    }
                                    AudioSpeakerButton(
                                        onClick = { viewModel.speak(res.correctedEnglish) }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Hindi Feedback & Rule Explanations
                            Text(
                                text = "शिक्षक की सलाह (Hindi Feedback):",
                                fontWeight = FontWeight.Bold,
                                color = RoyalBlueDark,
                                fontSize = 13.sp
                            )
                            Text(
                                text = res.feedbackHi,
                                fontSize = 12.sp,
                                color = Color(0xFF334155),
                                lineHeight = 18.sp,
                                modifier = Modifier.padding(top = 2.dp)
                            )

                            // Mistakes detailed list
                            if (res.mistakes.isNotEmpty()) {
                                Spacer(modifier = Modifier.height(10.dp))
                                res.mistakes.forEach { mis ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 4.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0xFFFEF2F2))
                                            .padding(10.dp)
                                    ) {
                                        Column {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(Icons.Default.Error, contentDescription = "Error", tint = ErrorRed, modifier = Modifier.size(16.dp))
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Text(
                                                    text = "गलती: '${mis.errorSnippet}' -> सही: '${mis.correction}'",
                                                    fontWeight = FontWeight.Bold,
                                                    color = ErrorRed,
                                                    fontSize = 12.sp
                                                )
                                            }
                                            Text(
                                                text = mis.explanationHi,
                                                fontSize = 11.sp,
                                                color = Color(0xFF7F1D1D),
                                                modifier = Modifier.padding(top = 4.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.GTranslate
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AudioSpeakerButton
import com.example.ui.components.SectionHeading
import com.example.ui.theme.FlameGold
import com.example.ui.theme.LightBlueSecondaryContainer
import com.example.ui.theme.RoyalBlueContainer
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.RoyalBluePrimary
import com.example.ui.theme.RoyalBlueVariant
import com.example.ui.theme.SuccessGreen
import com.example.ui.viewmodel.EnglishMasterViewModel

@Composable
fun TranslatorScreen(
    viewModel: EnglishMasterViewModel,
    modifier: Modifier = Modifier
) {
    BackHandler {
        viewModel.navigateBack()
    }

    val context = LocalContext.current
    val isEnglishToHindi by viewModel.isEnglishToHindiMode.collectAsState()
    val englishInput by viewModel.englishInput.collectAsState()
    val hindiInput by viewModel.hindiInput.collectAsState()
    val isGeminiTranslating by viewModel.isGeminiTranslating.collectAsState()
    val geminiResult by viewModel.geminiResult.collectAsState()
    val translationOutput by viewModel.currentTranslation.collectAsState()
    val savedTranslations by viewModel.savedTranslations.collectAsState()

    val quickEnglishPhrases = listOf(
        "Good morning, how are you?",
        "Could you please help me?",
        "I am learning English with AI.",
        "The weather is very pleasant.",
        "What time is the meeting?",
        "Where is the nearest metro station?",
        "Practice makes a man perfect."
    )

    val quickHindiPhrases = listOf(
        "आप कैसे हैं?",
        "मैं अंग्रेजी सीख रहा हूँ",
        "कृपया मेरी मदद करें",
        "मुझे भूख लग रही है",
        "कल मैं दिल्ली जाऊँगा",
        "समय क्या हुआ है?"
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "AI Text Translator",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.Default.AutoAwesome, contentDescription = "Gemini AI", tint = FlameGold, modifier = Modifier.size(16.dp))
                    }
                    Text(
                        text = "Gemini AI द्वारा संचालित सटीक अनुवाद व व्याकरण",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 11.sp
                        )
                    )
                }
            }
        }

        // Mode Selector: English -> Hindi vs Hindi -> English
        TabRow(
            selectedTabIndex = if (isEnglishToHindi) 0 else 1,
            containerColor = Color.White,
            contentColor = RoyalBluePrimary
        ) {
            Tab(
                selected = isEnglishToHindi,
                onClick = { viewModel.setTranslationMode(true) },
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "English → Hindi",
                            fontWeight = if (isEnglishToHindi) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.Default.AutoAwesome, contentDescription = "AI", tint = FlameGold, modifier = Modifier.size(14.dp))
                    }
                }
            )
            Tab(
                selected = !isEnglishToHindi,
                onClick = { viewModel.setTranslationMode(false) },
                text = {
                    Text(
                        text = "Hindi → English",
                        fontWeight = if (!isEnglishToHindi) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 13.sp
                    )
                }
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            if (isEnglishToHindi) {
                // ===== ENGLISH TO HINDI TRANSLATION MODULE (GEMINI AI API) =====
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                                Text(
                                    text = "Enter English Text (अंग्रेजी में लिखें):",
                                    fontWeight = FontWeight.Bold,
                                    color = RoyalBlueDark,
                                    fontSize = 14.sp
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(RoyalBlueContainer)
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text("Gemini 3.5 Flash", color = RoyalBluePrimary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedTextField(
                                value = englishInput,
                                onValueChange = { viewModel.setEnglishInput(it) },
                                modifier = Modifier
                                    .testTag("english_translate_input")
                                    .fillMaxWidth(),
                                placeholder = { Text("e.g. Good morning! How can I help you today?", fontSize = 13.sp) },
                                shape = RoundedCornerShape(12.dp),
                                maxLines = 4,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = RoyalBluePrimary,
                                    unfocusedBorderColor = Color(0xFFCBD5E1)
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = { viewModel.translateEnglishToHindi() },
                                enabled = !isGeminiTranslating,
                                modifier = Modifier
                                    .testTag("translate_gemini_button")
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = RoyalBluePrimary)
                            ) {
                                if (isGeminiTranslating) {
                                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Translating with Gemini AI...", fontWeight = FontWeight.Bold)
                                } else {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.AutoAwesome, contentDescription = "Translate", tint = FlameGold, modifier = Modifier.size(18.dp))
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Translate to Hindi (हिन्दी अनुवाद करें)", fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Quick English Phrases
                item {
                    Text(
                        text = "Quick English Phrases (त्वरित उदाहरण चुनें):",
                        fontWeight = FontWeight.Bold,
                        color = RoyalBlueDark,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 4.dp)
                    ) {
                        items(quickEnglishPhrases) { phrase ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(RoyalBlueContainer)
                                    .clickable {
                                        viewModel.setEnglishInput(phrase)
                                        viewModel.translateEnglishToHindi(phrase)
                                    }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = phrase,
                                    color = RoyalBlueDark,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Gemini AI Result Card
                if (geminiResult != null) {
                    val res = geminiResult!!
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("gemini_translation_result_card"),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
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
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(
                                                if (res.isFromGeminiApi) FlameGold.copy(alpha = 0.2f) else RoyalBlueContainer
                                            )
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                Icons.Default.AutoAwesome,
                                                contentDescription = "AI",
                                                tint = if (res.isFromGeminiApi) FlameGold else RoyalBluePrimary,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = if (res.isFromGeminiApi) "Gemini AI Translation" else "AI Translation",
                                                fontWeight = FontWeight.Bold,
                                                color = if (res.isFromGeminiApi) Color(0xFFB45309) else RoyalBluePrimary,
                                                fontSize = 11.sp
                                            )
                                        }
                                    }

                                    Row {
                                        AudioSpeakerButton(
                                            onClick = { viewModel.speak(res.hindiText, isHindi = true) }
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        IconButton(
                                            onClick = {
                                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                                val clip = ClipData.newPlainText("Hindi", res.hindiText)
                                                clipboard.setPrimaryClip(clip)
                                                Toast.makeText(context, "Hindi translation copied!", Toast.LENGTH_SHORT).show()
                                            }
                                        ) {
                                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = RoyalBluePrimary)
                                        }
                                        Spacer(modifier = Modifier.width(2.dp))
                                        IconButton(onClick = { viewModel.saveGeminiTranslation() }) {
                                            Icon(Icons.Default.Bookmark, contentDescription = "Save", tint = FlameGold)
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = res.hindiText,
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = RoyalBlueDark,
                                        fontSize = 18.sp
                                    )
                                )

                                if (res.pronunciationHi.isNotBlank()) {
                                    Text(
                                        text = "उच्चारण: ${res.pronunciationHi}",
                                        color = RoyalBlueVariant,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }

                                if (res.grammaticalExplanationHi.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(LightBlueSecondaryContainer)
                                            .padding(12.dp)
                                    ) {
                                        Column {
                                            Text(
                                                text = "व्याकरण व संदर्भ (Explanation):",
                                                fontWeight = FontWeight.Bold,
                                                color = RoyalBlueDark,
                                                fontSize = 12.sp
                                            )
                                            Text(
                                                text = res.grammaticalExplanationHi,
                                                fontSize = 12.sp,
                                                color = Color(0xFF1E293B),
                                                modifier = Modifier.padding(top = 2.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            } else {
                // ===== HINDI TO ENGLISH TRANSLATION MODE =====
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Enter Hindi Sentence (हिन्दी वाक्य लिखें):",
                                fontWeight = FontWeight.Bold,
                                color = RoyalBlueDark,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = hindiInput,
                                onValueChange = { viewModel.setHindiInput(it) },
                                modifier = Modifier
                                    .testTag("hindi_translate_input")
                                    .fillMaxWidth(),
                                placeholder = { Text("जैसे: आप कैसे हैं? / मैं कल आऊँगा...", fontSize = 13.sp) },
                                shape = RoundedCornerShape(12.dp),
                                maxLines = 3,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = RoyalBluePrimary,
                                    unfocusedBorderColor = Color(0xFFCBD5E1)
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = { viewModel.performTranslation() },
                                modifier = Modifier
                                    .testTag("translate_button")
                                    .fillMaxWidth()
                                    .height(48.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = RoyalBluePrimary)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.GTranslate, contentDescription = "Translate", modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Translate to English (अंग्रेजी में बदलें)", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Quick Suggestions Chips for Hindi
                item {
                    Text(
                        text = "Quick Phrases (अक्सर बोले जाने वाले वाक्य):",
                        fontWeight = FontWeight.Bold,
                        color = RoyalBlueDark,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 4.dp)
                    ) {
                        items(quickHindiPhrases) { phrase ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(RoyalBlueContainer)
                                    .clickable {
                                        viewModel.setHindiInput(phrase)
                                        viewModel.performTranslation(phrase)
                                    }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = phrase,
                                    color = RoyalBlueDark,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Hindi to English Output Card
                if (translationOutput != null) {
                    val out = translationOutput!!
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("translation_output_card"),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
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
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(RoyalBlueContainer)
                                            .padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = out.tenseUsed,
                                            fontWeight = FontWeight.Bold,
                                            color = RoyalBluePrimary,
                                            fontSize = 11.sp
                                        )
                                    }

                                    Row {
                                        AudioSpeakerButton(
                                            onClick = { viewModel.speak(out.english) }
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        IconButton(
                                            onClick = {
                                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                                val clip = ClipData.newPlainText("English", out.english)
                                                clipboard.setPrimaryClip(clip)
                                                Toast.makeText(context, "English text copied!", Toast.LENGTH_SHORT).show()
                                            }
                                        ) {
                                            Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = RoyalBluePrimary)
                                        }
                                        Spacer(modifier = Modifier.width(2.dp))
                                        IconButton(onClick = { viewModel.saveCurrentTranslation() }) {
                                            Icon(Icons.Default.Bookmark, contentDescription = "Save", tint = FlameGold)
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = out.english,
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = RoyalBlueDark
                                    )
                                )

                                Text(
                                    text = "उच्चारण: ${out.pronunciationHi}",
                                    color = RoyalBlueVariant,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(top = 2.dp)
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(LightBlueSecondaryContainer)
                                        .padding(12.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = "व्याकरण व्याख्या (Why this translation?):",
                                            fontWeight = FontWeight.Bold,
                                            color = RoyalBlueDark,
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = out.grammarExplanationHi,
                                            fontSize = 12.sp,
                                            color = Color(0xFF1E293B),
                                            modifier = Modifier.padding(top = 2.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

            // Saved Translations Section
            if (savedTranslations.isNotEmpty()) {
                item {
                    SectionHeading(
                        titleEn = "Saved Translations",
                        titleHi = "सहेजे गए अनुवाद",
                        actionText = "${savedTranslations.size} Saved"
                    )
                }

                items(savedTranslations) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = item.englishText,
                                    fontWeight = FontWeight.Bold,
                                    color = RoyalBlueDark,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "हिन्दी: ${item.hindiText}",
                                    color = Color(0xFF64748B),
                                    fontSize = 12.sp
                                )
                            }
                            AudioSpeakerButton(
                                onClick = { viewModel.speak(item.englishText) },
                                sizeDp = 32
                            )
                            IconButton(onClick = { viewModel.deleteSavedTranslation(item.id) }) {
                                Icon(Icons.Default.DeleteOutline, contentDescription = "Delete", tint = Color(0xFF94A3B8))
                            }
                        }
                    }
                }
            }
        }
    }
}

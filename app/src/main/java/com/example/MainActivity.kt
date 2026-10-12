package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.AdminScreen
import com.example.ui.screens.AiTeacherScreen
import com.example.ui.screens.ConversationDetailScreen
import com.example.ui.screens.ConversationScreen
import com.example.ui.screens.CoursesScreen
import com.example.ui.screens.GrammarScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LessonDetailScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SpeakingScreen
import com.example.ui.screens.TranslatorScreen
import com.example.ui.screens.VocabularyScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.RoyalBlueContainer
import com.example.ui.theme.RoyalBlueDark
import com.example.ui.theme.RoyalBluePrimary
import com.example.ui.viewmodel.EnglishMasterViewModel
import com.example.ui.viewmodel.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val viewModel: EnglishMasterViewModel = viewModel()
                EnglishMasterApp(viewModel)
            }
        }
    }
}

@Composable
fun EnglishMasterApp(viewModel: EnglishMasterViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()

    BackHandler(enabled = currentScreen != Screen.HOME) {
        viewModel.navigateBack()
    }

    val showBottomBar = currentScreen in listOf(
        Screen.HOME,
        Screen.COURSES,
        Screen.SPEAKING,
        Screen.VOCABULARY,
        Screen.AI_TEACHER
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.statusBars,
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color.White,
                    contentColor = RoyalBluePrimary,
                    tonalElevation = 6.dp
                ) {
                    NavigationBarItem(
                        selected = currentScreen == Screen.HOME,
                        onClick = { viewModel.navigateTo(Screen.HOME) },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text("Home", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        modifier = Modifier.testTag("bottom_nav_home"),
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = RoyalBluePrimary,
                            selectedTextColor = RoyalBluePrimary,
                            indicatorColor = RoyalBlueContainer
                        )
                    )
                    NavigationBarItem(
                        selected = currentScreen == Screen.COURSES,
                        onClick = { viewModel.navigateTo(Screen.COURSES) },
                        icon = { Icon(Icons.Default.School, contentDescription = "Courses") },
                        label = { Text("Courses", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        modifier = Modifier.testTag("bottom_nav_courses"),
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = RoyalBluePrimary,
                            selectedTextColor = RoyalBluePrimary,
                            indicatorColor = RoyalBlueContainer
                        )
                    )
                    NavigationBarItem(
                        selected = currentScreen == Screen.SPEAKING,
                        onClick = { viewModel.navigateTo(Screen.SPEAKING) },
                        icon = { Icon(Icons.Default.Mic, contentDescription = "Speaking") },
                        label = { Text("Speaking", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        modifier = Modifier.testTag("bottom_nav_speaking"),
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = RoyalBluePrimary,
                            selectedTextColor = RoyalBluePrimary,
                            indicatorColor = RoyalBlueContainer
                        )
                    )
                    NavigationBarItem(
                        selected = currentScreen == Screen.VOCABULARY,
                        onClick = { viewModel.navigateTo(Screen.VOCABULARY) },
                        icon = { Icon(Icons.Default.Book, contentDescription = "Vocab") },
                        label = { Text("Vocab", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        modifier = Modifier.testTag("bottom_nav_vocab"),
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = RoyalBluePrimary,
                            selectedTextColor = RoyalBluePrimary,
                            indicatorColor = RoyalBlueContainer
                        )
                    )
                    NavigationBarItem(
                        selected = currentScreen == Screen.AI_TEACHER,
                        onClick = { viewModel.navigateTo(Screen.AI_TEACHER) },
                        icon = { Icon(Icons.Default.SmartToy, contentDescription = "Guru AI") },
                        label = { Text("Guru AI", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        modifier = Modifier.testTag("bottom_nav_ai_teacher"),
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = RoyalBluePrimary,
                            selectedTextColor = RoyalBluePrimary,
                            indicatorColor = RoyalBlueContainer
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                Screen.HOME -> HomeScreen(viewModel)
                Screen.COURSES -> CoursesScreen(viewModel)
                Screen.LESSON_DETAIL -> LessonDetailScreen(viewModel)
                Screen.VOCABULARY -> VocabularyScreen(viewModel)
                Screen.SPEAKING -> SpeakingScreen(viewModel)
                Screen.GRAMMAR -> GrammarScreen(viewModel)
                Screen.TRANSLATOR -> TranslatorScreen(viewModel)
                Screen.CONVERSATIONS -> ConversationScreen(viewModel)
                Screen.CONVERSATION_DETAIL -> ConversationDetailScreen(viewModel)
                Screen.QUIZ -> QuizScreen(viewModel)
                Screen.AI_TEACHER -> AiTeacherScreen(viewModel)
                Screen.ADMIN -> AdminScreen(viewModel)
                Screen.PROFILE -> ProfileScreen(viewModel)
            }
        }
    }
}

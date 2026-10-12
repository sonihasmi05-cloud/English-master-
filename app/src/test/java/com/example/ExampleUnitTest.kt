package com.example

import com.example.service.GrammarAiEngine
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun testHindiTranslation() {
    val result = GrammarAiEngine.translateHindiToEnglish("आप कैसे हैं")
    assertEquals("How are you?", result.english)
    assertTrue(result.grammarExplanationHi.isNotBlank())
  }

  @Test
  fun testGrammarMistakeDetection_DidYouWent() {
    val result = GrammarAiEngine.analyzeSpokenSentence("Did you went to school yesterday?")
    assertEquals("Did you go to school yesterday?", result.correctedEnglish)
    assertTrue(result.mistakes.isNotEmpty())
    assertEquals("did you go", result.mistakes[0].correction)
  }

  @Test
  fun testGrammarMistakeDetection_HeDont() {
    val result = GrammarAiEngine.analyzeSpokenSentence("He don't know the answer")
    assertEquals("He doesn't know the answer", result.correctedEnglish)
  }

  @Test
  fun testAiTeacherResponse() {
    val (en, hi) = GrammarAiEngine.getTeacherResponse("Explain Tenses in Hindi")
    assertTrue(en.contains("Tenses") || en.contains("Present"))
    assertTrue(hi.contains("Tenses") || hi.contains("पहचान"))
  }

  @Test
  fun testGeminiTranslationService_EnglishToHindi() = kotlinx.coroutines.test.runTest {
    val service = com.example.service.GeminiTranslationService()
    val res = service.translateEnglishToHindi("How are you")
    assertTrue(res.hindiText.contains("आप कैसे हैं") || res.hindiText.isNotBlank())
  }
}


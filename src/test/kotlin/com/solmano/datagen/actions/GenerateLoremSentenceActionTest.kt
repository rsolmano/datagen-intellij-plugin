package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GenerateLoremSentenceActionTest : BasePlatformTestCase() {

    fun `test lorem sentence generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = GenerateLoremSentenceAction()
            val event = myFixture.testAction(action)
        }

        val documentText = editor.document.text
        assertTrue("Document should not be empty after lorem sentence insertion", documentText.isNotEmpty())

        // Validate the inserted text is a valid sentence
        assertTrue("Lorem sentence should start with capital letter: '$documentText'", documentText.first().isUpperCase())
        assertTrue("Lorem sentence should end with period: '$documentText'", documentText.endsWith("."))
        assertTrue("Lorem sentence should contain spaces: '$documentText'", documentText.contains(" "))
        
        val wordCount = documentText.dropLast(1).split(" ").size // Remove period and count words
        assertTrue("Lorem sentence should have 8-15 words, got $wordCount: '$documentText'", wordCount in 8..15)
    }

    fun `test lorem sentence length estimation`() {
        val sentences = mutableListOf<String>()
        
        repeat(50) {
            val file = myFixture.configureByText("testFile$it.kt", "")
            val editor: Editor = myFixture.editor

            WriteCommandAction.runWriteCommandAction(project) {
                val action = GenerateLoremSentenceAction()
                val event = myFixture.testAction(action)
            }

            val sentence = editor.document.text
            sentences.add(sentence)
        }
        
        // Calculate estimated character count for sentences
        val avgSentenceLength = sentences.map { it.length }.average()
        
        // A sentence with 8-15 words should be roughly 40-120 characters
        assertTrue("Average sentence length should be reasonable: $avgSentenceLength characters", avgSentenceLength in 40.0..120.0)
    }
}
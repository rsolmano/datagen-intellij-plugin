package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GenerateLoremLongParagraphActionTest : BasePlatformTestCase() {

    fun `test lorem long paragraph generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = GenerateLoremLongParagraphAction()
            val event = myFixture.testAction(action)
        }

        val documentText = editor.document.text
        assertTrue("Document should not be empty after lorem long paragraph insertion", documentText.isNotEmpty())

        // Validate the inserted text is a valid paragraph
        assertTrue("Lorem paragraph should start with capital letter: '$documentText'", documentText.first().isUpperCase())
        assertTrue("Lorem paragraph should end with period: '$documentText'", documentText.endsWith("."))
        
        val sentenceCount = documentText.split(".").size - 1 // Count sentences (minus 1 for the last empty part)
        assertTrue("Lorem long paragraph should have 8-11 sentences, got $sentenceCount: '$documentText'", sentenceCount in 8..11)
    }

    fun `test lorem paragraph length estimation`() {
        val shortParagraphs = mutableListOf<String>()
        val longParagraphs = mutableListOf<String>()
        
        repeat(20) {
            // Test short paragraphs
            val shortFile = myFixture.configureByText("shortFile$it.kt", "")
            val shortEditor: Editor = myFixture.editor
            WriteCommandAction.runWriteCommandAction(project) {
                val action = GenerateLoremShortParagraphAction()
                val event = myFixture.testAction(action)
            }
            shortParagraphs.add(shortEditor.document.text)
            
            // Test long paragraphs
            val longFile = myFixture.configureByText("longFile$it.kt", "")
            val longEditor: Editor = myFixture.editor
            WriteCommandAction.runWriteCommandAction(project) {
                val action = GenerateLoremLongParagraphAction()
                val event = myFixture.testAction(action)
            }
            longParagraphs.add(longEditor.document.text)
        }
        
        // Calculate average lengths
        val avgShortLength = shortParagraphs.map { it.length }.average()
        val avgLongLength = longParagraphs.map { it.length }.average()
        
        // Long paragraphs should be longer than short ones
        assertTrue("Long paragraphs should be longer than short paragraphs: short=$avgShortLength, long=$avgLongLength", 
                  avgLongLength > avgShortLength)
        
        // Expected character counts based on sentence structure
        assertTrue("Short paragraphs should be reasonably sized: $avgShortLength characters", avgShortLength in 150.0..600.0)
        assertTrue("Long paragraphs should be reasonably sized: $avgLongLength characters", avgLongLength in 400.0..1200.0)
    }
}
package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GenerateLoremWordActionTest : BasePlatformTestCase() {

    fun `test lorem word generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = GenerateLoremWordAction()
            val event = myFixture.testAction(action)
        }

        val documentText = editor.document.text
        assertTrue("Document should not be empty after lorem word insertion", documentText.isNotEmpty())

        // Validate the inserted text is a valid lorem ipsum word
        assertTrue("Lorem word should contain only letters: '$documentText'", documentText.all { it.isLetter() })
        assertTrue("Lorem word should be at least 2 characters: '$documentText'", documentText.length >= 2)
        assertTrue("Lorem word should not contain spaces: '$documentText'", !documentText.contains(" "))
    }

    fun `test lorem generation variety`() {
        val generatedWords = mutableSetOf<String>()
        
        repeat(100) {
            val file = myFixture.configureByText("testFile$it.kt", "")
            val editor: Editor = myFixture.editor

            WriteCommandAction.runWriteCommandAction(project) {
                val action = GenerateLoremWordAction()
                val event = myFixture.testAction(action)
            }

            val word = editor.document.text
            generatedWords.add(word)
        }
        
        // Should generate a variety of different lorem words
        assertTrue("Should generate variety of lorem words, got ${generatedWords.size} unique words", generatedWords.size >= 20)
        
        // Check for common lorem words
        val commonLoremWords = setOf("lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit")
        val hasCommonWords = generatedWords.intersect(commonLoremWords).isNotEmpty()
        assertTrue("Should include common lorem words", hasCommonWords)
    }
}
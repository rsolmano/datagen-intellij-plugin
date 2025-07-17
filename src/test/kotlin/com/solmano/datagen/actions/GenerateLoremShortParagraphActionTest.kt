package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GenerateLoremShortParagraphActionTest : BasePlatformTestCase() {

    fun `test lorem short paragraph generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = GenerateLoremShortParagraphAction()
            val event = myFixture.testAction(action)
        }

        val documentText = editor.document.text
        assertTrue("Document should not be empty after lorem short paragraph insertion", documentText.isNotEmpty())

        // Validate the inserted text is a valid paragraph
        assertTrue("Lorem paragraph should start with capital letter: '$documentText'", documentText.first().isUpperCase())
        assertTrue("Lorem paragraph should end with period: '$documentText'", documentText.endsWith("."))
        
        val sentenceCount = documentText.split(".").size - 1 // Count sentences (minus 1 for the last empty part)
        assertTrue("Lorem short paragraph should have 3-5 sentences, got $sentenceCount: '$documentText'", sentenceCount in 3..5)
    }
}
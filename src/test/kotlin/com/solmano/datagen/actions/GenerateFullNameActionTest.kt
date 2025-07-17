package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GenerateFullNameActionTest : BasePlatformTestCase() {

    fun `test full name generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = GenerateFullNameAction()
            val event = myFixture.testAction(action)
        }

        val documentText = editor.document.text
        assertTrue("Document should not be empty after full name insertion", documentText.isNotEmpty())

        // Validate the inserted text is a valid full name (first + space + last)
        assertTrue("Full name should contain exactly one space: '$documentText'", documentText.count { it == ' ' } == 1)
        
        val parts = documentText.split(" ")
        assertEquals("Full name should have exactly two parts: '$documentText'", 2, parts.size)
        
        val firstName = parts[0]
        val lastName = parts[1]
        
        assertTrue("First name should start with capital letter: '$firstName'", firstName.first().isUpperCase())
        assertTrue("Last name should start with capital letter: '$lastName'", lastName.first().isUpperCase())
        assertTrue("First name should be at least 2 characters: '$firstName'", firstName.length >= 2)
        assertTrue("Last name should be at least 2 characters: '$lastName'", lastName.length >= 2)
    }
}
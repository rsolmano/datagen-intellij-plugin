package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GenerateFirstNameActionTest : BasePlatformTestCase() {

    fun `test first name generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = GenerateFirstNameAction()
            val event = myFixture.testAction(action)
        }

        val documentText = editor.document.text
        assertTrue("Document should not be empty after first name insertion", documentText.isNotEmpty())

        // Validate the inserted text is a valid name (no spaces, starts with capital letter)
        assertTrue("First name should start with capital letter: '$documentText'", documentText.first().isUpperCase())
        assertTrue("First name should not contain spaces: '$documentText'", !documentText.contains(" "))
        assertTrue("First name should contain only letters: '$documentText'", documentText.all { it.isLetter() })
        assertTrue("First name should be at least 2 characters: '$documentText'", documentText.length >= 2)
    }

    fun `test first name variety and distribution`() {
        val generatedNames = mutableSetOf<String>()
        
        repeat(100) {
            val file = myFixture.configureByText("testFile$it.kt", "")
            val editor: Editor = myFixture.editor

            WriteCommandAction.runWriteCommandAction(project) {
                val action = GenerateFirstNameAction()
                val event = myFixture.testAction(action)
            }

            val name = editor.document.text
            generatedNames.add(name)
            
            // Validate each generated name
            assertTrue("First name should be valid: '$name'", name.isNotEmpty())
            assertTrue("First name should start with capital: '$name'", name.first().isUpperCase())
        }
        
        // Ensure we get variety in generated names (at least 20 different names in 100 generations)
        assertTrue("Should generate variety of first names, got ${generatedNames.size} unique names", generatedNames.size >= 20)
        
        // Check for cultural diversity - should have both Western and international names
        val westernNames = setOf("Alexander", "Andrew", "David", "James", "John", "Michael", "Robert", "William")
        val internationalNames = setOf("Akira", "Ali", "Chen", "Diego", "Hassan", "Hiroshi", "Omar", "Wei")
        
        val hasWesternNames = generatedNames.intersect(westernNames).isNotEmpty()
        val hasInternationalNames = generatedNames.intersect(internationalNames).isNotEmpty()
        
        assertTrue("Should include Western names", hasWesternNames)
        assertTrue("Should include international names", hasInternationalNames)
    }
}
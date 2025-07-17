package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GenerateLastNameActionTest : BasePlatformTestCase() {

    fun `test last name generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = GenerateLastNameAction()
            val event = myFixture.testAction(action)
        }

        val documentText = editor.document.text
        assertTrue("Document should not be empty after last name insertion", documentText.isNotEmpty())

        // Validate the inserted text is a valid surname
        assertTrue("Last name should start with capital letter: '$documentText'", documentText.first().isUpperCase())
        assertTrue("Last name should not contain spaces: '$documentText'", !documentText.contains(" "))
        assertTrue("Last name should contain only letters or valid characters: '$documentText'", 
            documentText.all { it.isLetter() || it == 'ü' || it == 'ä' || it == 'ö' || it == 'ß' })
        assertTrue("Last name should be at least 2 characters: '$documentText'", documentText.length >= 2)
    }

    fun `test last name variety and distribution`() {
        val generatedNames = mutableSetOf<String>()
        
        repeat(100) {
            val file = myFixture.configureByText("testFile$it.kt", "")
            val editor: Editor = myFixture.editor

            WriteCommandAction.runWriteCommandAction(project) {
                val action = GenerateLastNameAction()
                val event = myFixture.testAction(action)
            }

            val name = editor.document.text
            generatedNames.add(name)
            
            // Validate each generated name
            assertTrue("Last name should be valid: '$name'", name.isNotEmpty())
            assertTrue("Last name should start with capital: '$name'", name.first().isUpperCase())
        }
        
        // Ensure we get variety in generated names (at least 20 different names in 100 generations)
        assertTrue("Should generate variety of last names, got ${generatedNames.size} unique names", generatedNames.size >= 20)
        
        // Check for cultural diversity - should have both Western and international names
        val westernNames = setOf("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis")
        val internationalNames = setOf("Chen", "Wang", "Li", "Singh", "Kumar", "Müller", "Sato", "Suzuki")
        
        val hasWesternNames = generatedNames.intersect(westernNames).isNotEmpty()
        val hasInternationalNames = generatedNames.intersect(internationalNames).isNotEmpty()
        
        assertTrue("Should include Western names", hasWesternNames)
        assertTrue("Should include international names", hasInternationalNames)
    }
}
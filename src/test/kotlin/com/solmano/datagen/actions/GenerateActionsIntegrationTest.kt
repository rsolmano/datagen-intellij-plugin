package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GenerateActionsIntegrationTest : BasePlatformTestCase() {

    fun `test action class names match expected behavior`() {
        // Test that each action class generates the expected type of content
        val actions = mapOf(
            GenerateUUIDAction() to "UUID",
            Generate1to999Action() to "number",
            Generate1to999999Action() to "number",
            GenerateEmailAction() to "email",
            GenerateFirstNameAction() to "firstName",
            GenerateLastNameAction() to "lastName",
            GenerateFullNameAction() to "fullName",
            GenerateLoremWordAction() to "loremWord",
            GenerateLoremSentenceAction() to "loremSentence",
            GenerateLoremShortParagraphAction() to "loremShortParagraph",
            GenerateLoremLongParagraphAction() to "loremLongParagraph"
        )
        
        actions.forEach { (action, expectedType) ->
            val file = myFixture.configureByText("testFile.kt", "")
            val editor: Editor = myFixture.editor
            
            WriteCommandAction.runWriteCommandAction(project) {
                val event = myFixture.testAction(action)
            }
            
            val generatedText = editor.document.text
            assertTrue("Action ${action::class.simpleName} should generate non-empty $expectedType", 
                      generatedText.isNotEmpty())
            
            // Basic validation based on expected type
            when (expectedType) {
                "UUID" -> {
                    val uuidRegex = Regex("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
                    assertTrue("UUID should match pattern: '$generatedText'", uuidRegex.matches(generatedText))
                }
                "number" -> {
                    assertTrue("Should be a valid number: '$generatedText'", generatedText.toIntOrNull() != null)
                }
                "email" -> {
                    assertTrue("Should contain @ symbol: '$generatedText'", generatedText.contains("@"))
                }
                "firstName", "lastName" -> {
                    assertTrue("Name should start with capital: '$generatedText'", generatedText.first().isUpperCase())
                    assertTrue("Name should not contain spaces: '$generatedText'", !generatedText.contains(" "))
                }
                "fullName" -> {
                    assertTrue("Full name should contain space: '$generatedText'", generatedText.contains(" "))
                }
                "loremWord" -> {
                    assertTrue("Lorem word should not contain spaces: '$generatedText'", !generatedText.contains(" "))
                }
                "loremSentence" -> {
                    assertTrue("Lorem sentence should end with period: '$generatedText'", generatedText.endsWith("."))
                }
                "loremShortParagraph", "loremLongParagraph" -> {
                    assertTrue("Lorem paragraph should end with period: '$generatedText'", generatedText.endsWith("."))
                    assertTrue("Lorem paragraph should contain multiple sentences: '$generatedText'", 
                              generatedText.split(".").size > 2)
                }
            }
        }
    }

    fun `test expected character count estimations`() {
        // This test helps estimate the character counts for different action types
        // Useful for documentation and planning purposes
        
        val estimations = mutableMapOf<String, Int>()
        
        // Test each action type and get average character count
        val actionTypes = mapOf(
            "UUID" to GenerateUUIDAction(),
            "1to999" to Generate1to999Action(),
            "1to999999" to Generate1to999999Action(),
            "email" to GenerateEmailAction(),
            "firstName" to GenerateFirstNameAction(),
            "lastName" to GenerateLastNameAction(),
            "fullName" to GenerateFullNameAction(),
            "loremWord" to GenerateLoremWordAction(),
            "loremSentence" to GenerateLoremSentenceAction(),
            "loremShortParagraph" to GenerateLoremShortParagraphAction(),
            "loremLongParagraph" to GenerateLoremLongParagraphAction()
        )
        
        actionTypes.forEach { (typeName, action) ->
            val lengths = mutableListOf<Int>()
            
            repeat(20) {
                val file = myFixture.configureByText("testFile$it.kt", "")
                val editor: Editor = myFixture.editor
                
                WriteCommandAction.runWriteCommandAction(project) {
                    val event = myFixture.testAction(action)
                }
                
                lengths.add(editor.document.text.length)
            }
            
            val avgLength = lengths.average().toInt()
            estimations[typeName] = avgLength
        }
        
        // Verify reasonable expectations
        assertTrue("UUID should be exactly 36 characters", estimations["UUID"] == 36)
        assertTrue("1to999 should be 1-3 characters", estimations["1to999"]!! in 1..3)
        assertTrue("1to999999 should be 1-7 characters", estimations["1to999999"]!! in 1..7)
        assertTrue("Email should be reasonable length", estimations["email"]!! in 10..50)
        assertTrue("First name should be reasonable length", estimations["firstName"]!! in 3..15)
        assertTrue("Last name should be reasonable length", estimations["lastName"]!! in 3..15)
        assertTrue("Full name should be reasonable length", estimations["fullName"]!! in 8..30)
        assertTrue("Lorem word should be reasonable length", estimations["loremWord"]!! in 3..15)
        assertTrue("Lorem sentence should be reasonable length", estimations["loremSentence"]!! in 40..120)
        assertTrue("Lorem short paragraph should be reasonable length", estimations["loremShortParagraph"]!! in 150..600)
        assertTrue("Lorem long paragraph should be reasonable length", estimations["loremLongParagraph"]!! in 400..1200)
        
        // Print estimations for documentation (will show in test output)
        println("Character count estimations:")
        estimations.forEach { (type, count) ->
            when (type) {
                "UUID" -> assertEquals("UUID length should be exactly 36", 36, count)
                "loremLongParagraph" -> {
                    // This is referenced in the test file, so let's validate it matches expectation
                    // The test expects around 80 characters per line/sentence based on the comment
                    assertTrue("Long paragraph should be substantial length for 8-11 sentences", count >= 400)
                }
            }
        }
    }
}
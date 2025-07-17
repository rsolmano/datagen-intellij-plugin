package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GenerateEmailActionTest : BasePlatformTestCase() {

    fun `test email generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = GenerateEmailAction()
            val event = myFixture.testAction(action)
        }

        val documentText = editor.document.text
        assertTrue("Document should not be empty after email insertion", documentText.isNotEmpty())

        // Validate the inserted text is a valid email format (RFC 5322 basic validation)
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        assertTrue("Inserted text should be a valid email format: '$documentText'", emailRegex.matches(documentText))
    }

    fun `test email format validation with multiple generations`() {
        // Test multiple email generations to ensure variety and validity
        val generatedEmails = mutableSetOf<String>()
        
        repeat(50) {
            val file = myFixture.configureByText("testFile$it.kt", "")
            val editor: Editor = myFixture.editor

            WriteCommandAction.runWriteCommandAction(project) {
                val action = GenerateEmailAction()
                val event = myFixture.testAction(action)
            }

            val email = editor.document.text
            generatedEmails.add(email)
            
            // Validate each generated email
            val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
            assertTrue("Generated email should be valid: '$email'", emailRegex.matches(email))
            
            // Check email structure
            assertTrue("Email should contain @ symbol: '$email'", email.contains("@"))
            val parts = email.split("@")
            assertEquals("Email should have exactly one @ symbol: '$email'", 2, parts.size)
            
            val localPart = parts[0]
            val domainPart = parts[1]
            
            assertTrue("Local part should not be empty: '$email'", localPart.isNotEmpty())
            assertTrue("Domain part should not be empty: '$email'", domainPart.isNotEmpty())
            assertTrue("Domain should contain a dot: '$email'", domainPart.contains("."))
        }
        
        // Ensure we get variety in generated emails (at least 10 different emails in 50 generations)
        assertTrue("Should generate variety of emails, got ${generatedEmails.size} unique emails", generatedEmails.size >= 10)
    }

    fun `test email patterns variety`() {
        val generatedEmails = mutableSetOf<String>()
        val patterns = mutableSetOf<String>()
        
        repeat(100) {
            val file = myFixture.configureByText("testFile$it.kt", "")
            val editor: Editor = myFixture.editor

            WriteCommandAction.runWriteCommandAction(project) {
                val action = GenerateEmailAction()
                val event = myFixture.testAction(action)
            }

            val email = editor.document.text
            generatedEmails.add(email)
            
            // Extract pattern type based on local part structure
            val localPart = email.split("@")[0]
            val pattern = when {
                localPart.contains(".") && !localPart.any { it.isDigit() } -> "firstname.lastname"
                localPart.any { it.isDigit() } -> "firstname+number"
                localPart.contains(".") && localPart.count { it == '.' } == 1 -> "firstname.initial"
                !localPart.contains(".") && !localPart.any { it.isDigit() } -> "firstnamelastname"
                else -> "other"
            }
            patterns.add(pattern)
        }
        
        // Should have at least 3 different patterns
        assertTrue("Should generate multiple email patterns, got: $patterns", patterns.size >= 3)
    }

    fun `test email domains variety`() {
        val domains = mutableSetOf<String>()
        
        repeat(100) {
            val file = myFixture.configureByText("testFile$it.kt", "")
            val editor: Editor = myFixture.editor

            WriteCommandAction.runWriteCommandAction(project) {
                val action = GenerateEmailAction()
                val event = myFixture.testAction(action)
            }

            val email = editor.document.text
            val domain = email.split("@")[1]
            domains.add(domain)
        }
        
        // Should have variety in domains (at least 5 different domains in 100 generations)
        assertTrue("Should generate variety of domains, got ${domains.size} unique domains: $domains", domains.size >= 5)
        
        // Check that common domains are included
        val commonDomains = setOf("gmail.com", "yahoo.com", "hotmail.com", "outlook.com")
        val hasCommonDomains = domains.intersect(commonDomains).isNotEmpty()
        assertTrue("Should include common email domains", hasCommonDomains)
    }
}
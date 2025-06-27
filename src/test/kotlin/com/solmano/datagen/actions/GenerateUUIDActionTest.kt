package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class SimpleGenerateAndInsertActionsTest : BasePlatformTestCase() {

    fun `test UUID generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = GenerateUUIDAction()
            val event = myFixture.testAction(action)
        }

        // Assert: Check that the document has a UUID inserted
        val documentText = editor.document.text
        assertTrue("Document should not be empty after UUID insertion", documentText.isNotEmpty())

        // Validate the inserted text is a valid UUID
        val uuidRegex = Regex("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}\$")
        assertTrue("Inserted text should be a valid UUID", uuidRegex.matches(documentText))
    }

    fun `test 1 to 999 generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = Generate1to999Action()
            val event = myFixture.testAction(action)
        }

        val documentText = editor.document.text
        assertTrue("Document should not be empty after inserting numbers from 1 to 999", documentText.isNotEmpty())

        val lines = documentText.split("\n")
        assertEquals("Number of lines should be just 1", 1, lines.size)
        lines.forEachIndexed { index, line ->
            val number = line.trim()
            assertTrue(
                "Line $index should contain a number from 1 to 999",
                number.toIntOrNull()?.let { it in 1..999 } ?: false)
        }
    }

    fun `test 1 to 999999 generation and insertion`() {
        val file = myFixture.configureByText("testFile.kt", "")
        val editor: Editor = myFixture.editor

        WriteCommandAction.runWriteCommandAction(project) {
            val action = Generate1to999999Action()
            val event = myFixture.testAction(action)
        }

        val documentText = editor.document.text
        assertTrue("Document should not be empty after inserting numbers from 1 to 999999", documentText.isNotEmpty())

        val lines = documentText.split("\n")
        assertEquals("Number of lines should be just 1", 1, lines.size)
        lines.forEachIndexed { index, line ->
            val number = line.trim()
            assertTrue(
                "Line $index should contain a number from 1 to 999999",
                number.toIntOrNull()?.let { it in 1..999_999 } ?: false)
        }
    }

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
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
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
            val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
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
        
        // Ensure we get variety in generated names (at least 25 different names in 100 generations)
        assertTrue("Should generate variety of last names, got ${generatedNames.size} unique names", generatedNames.size >= 25)
        
        // Check for cultural diversity
        val westernSurnames = setOf("Smith", "Johnson", "Williams", "Brown", "Garcia", "Miller")
        val asianSurnames = setOf("Chen", "Wang", "Li", "Singh", "Kumar", "Sato", "Suzuki")
        val europeanSurnames = setOf("Müller", "Schmidt", "Fischer", "Weber")
        
        val hasWesternSurnames = generatedNames.intersect(westernSurnames).isNotEmpty()
        val hasAsianSurnames = generatedNames.intersect(asianSurnames).isNotEmpty()
        val hasEuropeanSurnames = generatedNames.intersect(europeanSurnames).isNotEmpty()
        
        assertTrue("Should include Western surnames", hasWesternSurnames)
        assertTrue("Should include Asian surnames", hasAsianSurnames)
        assertTrue("Should include European surnames", hasEuropeanSurnames)
    }

    fun `test full name variety and realistic combinations`() {
        val generatedNames = mutableSetOf<String>()
        val firstNames = mutableSetOf<String>()
        val lastNames = mutableSetOf<String>()
        
        repeat(100) {
            val file = myFixture.configureByText("testFile$it.kt", "")
            val editor: Editor = myFixture.editor

            WriteCommandAction.runWriteCommandAction(project) {
                val action = GenerateFullNameAction()
                val event = myFixture.testAction(action)
            }

            val fullName = editor.document.text
            generatedNames.add(fullName)
            
            val parts = fullName.split(" ")
            firstNames.add(parts[0])
            lastNames.add(parts[1])
            
            // Validate each generated full name
            assertTrue("Full name should be valid: '$fullName'", fullName.isNotEmpty())
            assertEquals("Full name should have exactly two parts: '$fullName'", 2, parts.size)
        }
        
        // Ensure we get variety in generated names
        assertTrue("Should generate variety of full names, got ${generatedNames.size} unique names", generatedNames.size >= 50)
        assertTrue("Should generate variety of first names, got ${firstNames.size} unique first names", firstNames.size >= 20)
        assertTrue("Should generate variety of last names, got ${lastNames.size} unique last names", lastNames.size >= 25)
    }

    fun `test name generators do not produce empty or invalid names`() {
        val actions = listOf(
            GenerateFirstNameAction(),
            GenerateLastNameAction(),
            GenerateFullNameAction()
        )
        
        actions.forEach { action ->
            repeat(50) { iteration ->
                val file = myFixture.configureByText("testFile${action.javaClass.simpleName}$iteration.kt", "")
                val editor: Editor = myFixture.editor

                WriteCommandAction.runWriteCommandAction(project) {
                    val event = myFixture.testAction(action)
                }

                val result = editor.document.text
                
                // Basic validation
                assertTrue("Generated name should not be empty for ${action.javaClass.simpleName}: '$result'", result.isNotEmpty())
                assertTrue("Generated name should not be blank for ${action.javaClass.simpleName}: '$result'", result.isNotBlank())
                assertTrue("Generated name should not start with space for ${action.javaClass.simpleName}: '$result'", !result.startsWith(" "))
                assertTrue("Generated name should not end with space for ${action.javaClass.simpleName}: '$result'", !result.endsWith(" "))
                
                // Specific validation based on action type
                when (action) {
                    is GenerateFirstNameAction, is GenerateLastNameAction -> {
                        assertTrue("Single name should not contain spaces for ${action.javaClass.simpleName}: '$result'", !result.contains(" "))
                    }
                    is GenerateFullNameAction -> {
                        assertTrue("Full name should contain exactly one space for ${action.javaClass.simpleName}: '$result'", result.count { it == ' ' } == 1)
                    }
                }
            }
        }
    }
}

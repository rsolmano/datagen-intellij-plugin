package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class GenerateUUIDActionTest : BasePlatformTestCase() {

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
        val uuidRegex = Regex("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
        assertTrue("Inserted text should be a valid UUID", uuidRegex.matches(documentText))
    }
}
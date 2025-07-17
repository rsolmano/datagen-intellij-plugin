package com.solmano.datagen.actions

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class Generate1to999ActionTest : BasePlatformTestCase() {

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
}
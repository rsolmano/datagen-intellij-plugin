package com.solmano.datagen.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.CaretModel
import java.util.*
import kotlin.random.Random.Default.nextInt

abstract class SimpleGenerateAndInsertActions : AnAction() {

    protected abstract fun generate(): String
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project ?: return
        val editor = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR) ?: return

        WriteCommandAction.runWriteCommandAction(project) {
            val caretModel: CaretModel = editor.caretModel
            editor.document.insertString(caretModel.offset, generate())
        }
    }
}

class GenerateUUIDAction : SimpleGenerateAndInsertActions() {
    override fun generate(): String = UUID.randomUUID().toString()
}

class Generate1to999Action : SimpleGenerateAndInsertActions() {
    override fun generate(): String = (nextInt(1, 1000)).toString()
}

class Generate1to999999Action : SimpleGenerateAndInsertActions() {
    override fun generate(): String = (nextInt(1, 100000)).toString()
}

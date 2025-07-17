package com.solmano.datagen.actions

import java.util.*

class GenerateUUIDAction : BaseGenerateAction() {
    override fun generate(): String = UUID.randomUUID().toString()
}
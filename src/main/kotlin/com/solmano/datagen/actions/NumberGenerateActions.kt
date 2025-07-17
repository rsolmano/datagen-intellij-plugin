package com.solmano.datagen.actions

import kotlin.random.Random.Default.nextInt

class Generate1to999Action : BaseGenerateAction() {
    override fun generate(): String = (nextInt(1, 1000)).toString()
}

class Generate1to999999Action : BaseGenerateAction() {
    override fun generate(): String = (nextInt(1, 1000000)).toString()
}
package me.jorgecastillo.syntax

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.InputProcessor

fun InputProcessor.processInput(): Unit {
    Gdx.input.inputProcessor = this
}

fun processInput(listener : InputAdapter): Unit {
    Gdx.input.inputProcessor = listener
}

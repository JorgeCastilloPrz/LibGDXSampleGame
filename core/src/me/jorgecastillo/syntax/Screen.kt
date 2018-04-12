package me.jorgecastillo.syntax

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20

fun clearScreen(): Unit = Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

fun clearColor(r: Float = 0f,
               g: Float = 1f,
               b: Float = 0f,
               alpha: Float = 1f): Unit = Gdx.gl.glClearColor(r, g, b, alpha)

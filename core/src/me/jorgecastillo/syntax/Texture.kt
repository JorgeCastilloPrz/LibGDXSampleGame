package me.jorgecastillo.syntax

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion

fun Texture.splitRegions2D(frames: Int): Array<out Array<TextureRegion>> =
        TextureRegion.split(this, width / frames, height)

fun Texture.frames(count: Int): com.badlogic.gdx.utils.Array<TextureRegion> {
    val standingFrames = com.badlogic.gdx.utils.Array<TextureRegion>()
    val splitRegions = splitRegions2D(count)
    for (j in 0 until count) {
        standingFrames.add(splitRegions[0][j])
    }
    return standingFrames
}

fun Texture.animate(frameCount: Int, frameDuration: Float): Animation<TextureRegion> {
    return Animation(frameDuration, frames(frameCount))
}

package me.jorgecastillo

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import me.jorgecastillo.SampleGame.CurrentMainCharAnim.RUN
import me.jorgecastillo.SampleGame.CurrentMainCharAnim.STAND
import me.jorgecastillo.syntax.*

class SampleGame : ApplicationAdapter() {

    companion object {
        private const val STANDING_FRAMES = 80
        private const val RUNNING_FRAMES = 12
    }

    enum class CurrentMainCharAnim {
        STAND, RUN
    }

    private lateinit var camera: Camera
    private lateinit var viewport: Viewport
    private lateinit var mainCharStandingAnim: Animation<TextureRegion>
    private lateinit var mainCharRunAnim: Animation<TextureRegion>
    private lateinit var mainCharStandSheet: Texture
    private lateinit var mainCharRunSheet: Texture
    private lateinit var currentMainCharAnim: CurrentMainCharAnim
    private lateinit var spriteBatch: SpriteBatch
    private var elapsedTime: Float = 0f

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height)
    }

    override fun dispose() {
        spriteBatch.dispose()
        mainCharStandSheet.dispose()
    }

    override fun create() {
        camera = PerspectiveCamera()
        viewport = ScreenViewport(camera)
        loadSpriteSheets()
        processInput(inputListener())

        mainCharStandingAnim = mainCharStandSheet.animate(STANDING_FRAMES, 0.06f)
        mainCharRunAnim = mainCharRunSheet.animate(RUNNING_FRAMES, 0.04f)

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        spriteBatch = SpriteBatch()
        elapsedTime = 0f
        currentMainCharAnim = STAND
    }

    private fun loadSpriteSheets() {
        mainCharStandSheet = Texture(Gdx.files.internal("mainchar_standing.png"))
        mainCharRunSheet = Texture(Gdx.files.internal("mainchar_running.png"))
    }

    override fun render() {
        clearScreen()
        clearColor()
        elapsedTime += deltaTime() // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        val currentMainCharStandingFrame = mainCharStandingAnim.getKeyFrame(elapsedTime, true)
        val currentMainCharRunFrame = mainCharRunAnim.getKeyFrame(elapsedTime, true)
        val width = (mainCharStandSheet.width / STANDING_FRAMES) * 6f
        val height = (mainCharStandSheet.height) * 6f

        spriteBatch.begin()
        spriteBatch.draw(
                when (currentMainCharAnim) {
                    STAND -> currentMainCharStandingFrame
                    RUN -> currentMainCharRunFrame
                },
                50f,
                50f,
                width,
                height)

        spriteBatch.end()
    }

    private fun inputListener(): InputAdapter = object : InputAdapter() {
        override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
            currentMainCharAnim = when (currentMainCharAnim) {
                STAND -> RUN
                RUN -> STAND
            }
            return true
        }
    }
}

package info.stereohorse.pkit.fx

import processing.core.PApplet
import processing.core.PConstants

class Window : PApplet() {

    override fun settings() {
        fullScreen()
    }

    override fun draw() {
        clear()
    }

    override fun setup() {
        colorMode(PConstants.HSB, 360F, 100F, 100F)
    }
}
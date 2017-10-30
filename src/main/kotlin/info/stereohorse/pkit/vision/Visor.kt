package info.stereohorse.pkit.vision

import nu.pattern.OpenCV
import org.opencv.core.Mat
import org.opencv.videoio.VideoCapture

class Visor {

    private val videoCapture: VideoCapture


    init {
        OpenCV.loadLocally()

        videoCapture = VideoCapture(0)
    }

    fun loadImage(): Mat {
        val m = Mat()
        if (!videoCapture.read(m)) {
            throw RuntimeException("unable to read next frame from cam")
        }

        return m
    }
}
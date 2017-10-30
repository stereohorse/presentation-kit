package info.stereohorse.pkit.fx

import info.stereohorse.pkit.vision.Visor
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryUtil.NULL
import org.opencv.imgproc.Imgproc


class Window(val visor: Visor) {

    fun start() {

        GLFWErrorCallback.createPrint(System.err).set()

        if (!glfwInit()) {
            throw IllegalStateException("Unable to initialize GLFW")
        }

        val window = glfwCreateWindow(800, 600, "", NULL, NULL)
        if (window == NULL) {
            throw RuntimeException("Failed to create the GLFW window")
        }

        glfwMakeContextCurrent(window)
        glfwSwapInterval(1)

        GL.createCapabilities()

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f)

        MemoryStack.stackPush().use { stack ->
            val width = stack.ints(1)
            val height = stack.ints(1)

            glfwGetFramebufferSize(window, width, height)

            println("${width.get(0)} x ${height.get(0)}")
        }

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

            val img = visor.loadImage()
//            Imgproc.cvtColor()

            glfwSwapBuffers(window)
            glfwPollEvents()
        }

        glfwFreeCallbacks(window)
        glfwDestroyWindow(window)

        glfwTerminate()
        glfwSetErrorCallback(null).free()
    }
}
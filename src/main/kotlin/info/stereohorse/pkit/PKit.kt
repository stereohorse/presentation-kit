package info.stereohorse.pkit

import info.stereohorse.pkit.api.Api
import info.stereohorse.pkit.domain.Whiteboard
import info.stereohorse.pkit.socket.SocketServer


fun main(vararg args: String) {

    val whiteBoard = Whiteboard()

    Api(whiteBoard).start()
    SocketServer(whiteBoard).start()
}

package info.stereohorse.pkit.socket

import com.corundumstudio.socketio.Configuration
import com.corundumstudio.socketio.SocketIOServer
import info.stereohorse.pkit.domain.Whiteboard
import org.slf4j.LoggerFactory


class SocketServer(private val whiteboard: Whiteboard) {

    private val log = LoggerFactory.getLogger(SocketIOServer::class.java)


    fun start() {
        val config = Configuration().apply {
            port = 5678
            socketConfig.isReuseAddress = true
        }

        val server = SocketIOServer(config)

        server.addConnectListener { client ->
            log.info("connect: ${client.remoteAddress}")
            client.sendEvent("whiteboard", whiteboard.draws())
        }

        server.addDisconnectListener { client ->
            log.info("disconnect: ${client.remoteAddress}")
        }

        whiteboard.observe { event ->
            server.broadcastOperations.sendEvent("whiteboard-change", event)
        }

        server.start()
    }
}

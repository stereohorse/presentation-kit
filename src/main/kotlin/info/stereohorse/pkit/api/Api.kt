package info.stereohorse.pkit.api

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import info.stereohorse.pkit.domain.Event
import info.stereohorse.pkit.domain.Whiteboard
import io.ktor.application.call
import io.ktor.content.readText
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class Api(private val whiteboard: Whiteboard) {

    private val objMapper = jacksonObjectMapper()


    fun start() {

        val server = embeddedServer(Netty, 8080) {
            routing {
                post("/events") {
                    val json = call.request.receiveContent().readText()
                    val event: Event = objMapper.readValue(json)

                    whiteboard.handle(event)
                }
            }
        }
        server.start()
    }
}



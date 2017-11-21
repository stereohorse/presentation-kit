package info.stereohorse.pkit.domain

import info.stereohorse.pkit.domain.EventType.*

class Whiteboard {

    private val observers: MutableList<(Event) -> Unit> = mutableListOf()
    private var draws = OrderedEvents()


    fun handle(event: Event) {

        @Suppress("NON_EXHAUSTIVE_WHEN")
        when (event.type) {
            CLEAR -> draws.clean()
            DRAW, DRAW_START -> draws += event
        }

        println(event.type)

        observers.forEach {
            it(event)
        }
    }

    fun draws(): List<Event> = draws.toList()

    fun observe(observer: (Event) -> Unit) {
        observers += observer
    }
}
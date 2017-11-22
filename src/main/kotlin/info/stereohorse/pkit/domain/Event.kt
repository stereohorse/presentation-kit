package info.stereohorse.pkit.domain


data class Event(val type: EventType?,
                 val x: Float?,
                 val y: Float?,
                 val dx: Float?,
                 val dy: Float?,
                 val mousePressed: Boolean?,
                 val mouseReleased: Boolean?) {

    @Suppress("unused")
    constructor() : this(null, null, null, null, null, null, null)
}

enum class EventType {
    MOVE_POINTER,
    DRAW, DRAW_START,
    CLEAR,
    RESET,
    MOVE_CANVAS
}

data class OrderedEvents(private var lastEvent: OrderedEvent?) {

    constructor() : this(null)

    operator fun plusAssign(event: Event) {
        lastEvent = if (lastEvent == null) {
            OrderedEvent(event)
        } else {
            lastEvent?.append(event)
        }
    }

    fun clean() {
        lastEvent = null
    }

    fun toList(): List<Event> = lastEvent?.toList() ?: emptyList()
}


data class OrderedEvent(var prev: OrderedEvent?,
                        val event: Event) : Iterable<Event> {

    constructor(event: Event) : this(null, event)

    override fun iterator(): Iterator<Event> = OrderedEventsIterator(this)

    fun append(event: Event): OrderedEvent = OrderedEvent(this, event)

    fun toList(): List<Event> = this.map { it }
}

class OrderedEventsIterator(private var current: OrderedEvent?) : Iterator<Event> {

    override fun hasNext(): Boolean = current != null

    override fun next(): Event {
        val point = current?.event ?: throw IllegalStateException()
        current = current?.prev

        return point
    }

}

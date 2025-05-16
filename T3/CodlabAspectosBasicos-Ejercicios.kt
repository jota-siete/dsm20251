// Tarea 1 y 2: Crear clase Event y enum Daypart
enum class Daypart {
    MORNING,
    AFTERNOON,
    EVENING,
}

data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int,
)

// Tarea 7: Propiedad extendida para duración (tiene que ir antes de main)
val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) "corta" else "larga"

fun main() {
    // Tarea 3: Crear eventos
    val event1 = Event(title = "Despertarse", description = "Hora de levantarse", daypart = Daypart.MORNING, durationInMinutes = 0)
    val event2 = Event(title = "Desayunar", daypart = Daypart.MORNING, durationInMinutes = 15)
    val event3 = Event(title = "Aprender Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30)
    val event4 = Event(title = "Practicar Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60)
    val event5 = Event(title = "Ver el último video de DevBytes", daypart = Daypart.AFTERNOON, durationInMinutes = 10)
    val event6 = Event(title = "Revisar nueva librería de Android Jetpack", daypart = Daypart.EVENING, durationInMinutes = 45)

    val events = mutableListOf(event1, event2, event3, event4, event5, event6)

    println("Tarea 3:")
    events.forEach { event ->
        println("${event.title} - Duración: ${event.durationInMinutes} minutos")
    }
    println()
    
    // Tarea 4: Filtrar eventos cortos (< 60 minutos)
    val shortEvents = events.filter { it.durationInMinutes < 60 }
    println("Tarea 4:")
    println("Tienes ${shortEvents.size} eventos cortos.\n")

    // Tarea 5: Agrupar por Daypart
    val groupedEvents = events.groupBy { it.daypart }
    println("Tarea 5:")
    groupedEvents.forEach { (daypart, eventos) ->
        println("$daypart: ${eventos.size} eventos")
    }
    println()

    // Tarea 6: Último evento del día
    println("Tarea 6:")
    println("Último evento del día: ${events.last().title}\n")

    // Tarea 7: Usar propiedad extendida
    println("Tarea 7:")
    println("Duración del primer evento del día: ${events[0].durationOfEvent}")
}
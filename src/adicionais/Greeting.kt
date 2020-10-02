package adicionais

import java.util.*

fun Greeting(): String {
    val calendar = Calendar.getInstance()

    return when (calendar.get(Calendar.HOUR_OF_DAY)) {
        in 0..12 -> {
            "Bom Dia"
        }
        in 13..17 -> {
            "Boa Tarde"
        }
        in 18..23 -> {
            "Boa Noite"
        }
        else -> ""
    }


}
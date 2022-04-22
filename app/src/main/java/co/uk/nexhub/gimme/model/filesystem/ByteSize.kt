package co.uk.nexhub.gimme.model.filesystem

class ByteSize(bytes: Long) {
    private val BYTE_UNIT = 1000.toDouble()

    var bytes: Double = bytes.toDouble()

    fun update(bytes: Long) { this.bytes = bytes.toDouble() }

    private fun kb(): Double {
        return (bytes / BYTE_UNIT)
    }

    private fun mb(): Double {
        return (kb() / BYTE_UNIT)
    }

    private fun gb(): Double {
        return (mb() / BYTE_UNIT)
    }

    private fun tb(): Double {
        return (gb() / BYTE_UNIT)
    }

    fun unit(bytes: Double = this.bytes): Double {
        return when {
            bytes < 1000L -> bytes
            bytes < 1000000L -> kb()
            bytes < 1000000000L -> mb()
            bytes < 1000000000000L -> gb()
            bytes < 1000000000000000L -> tb()
            else -> {tb()}
        }
    }

    private fun unitLabel(bytes: Double = this.bytes): String {
        return when {
            bytes < 1000L -> "B"
            bytes < 1000000L -> "KB"
            bytes < 1000000000L -> "MB"
            bytes < 1000000000000L -> "GB"
            bytes < 1000000000000000L -> "TB"
            else -> {"TB"}
        }
    }

    override fun toString(): String {
        return "${round(unit())}${unitLabel()}"
    }

    private fun round(units: Double): Double {
        return String.format("%.1f", units).toDouble()
    }
}
package enum

enum class Mode(private val value: Int) {
    Standard(0),
    Taiko(1),
    Catch(2),
    Mania(3);

    override fun toString() = value.toString()
}

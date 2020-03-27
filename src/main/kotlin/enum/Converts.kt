package enum

enum class Converts(private val value: Int) {
    Include(0),
    NotInclude(1);

    override fun toString() = value.toString()
}

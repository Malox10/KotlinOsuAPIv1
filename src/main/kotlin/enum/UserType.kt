package enum

enum class UserType(private val value: String) {
    Id("id"),
    Name("string");

    override fun toString() = value
}

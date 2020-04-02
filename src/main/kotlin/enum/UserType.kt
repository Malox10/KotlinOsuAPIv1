package enum

enum class UserType(private val value: String) {
    Id("id"),
    Username("string");

    override fun toString() = value
}

package enum

@Suppress("EnumEntryName")
enum class UserType(private val value: String) {
    id("id"),
    string("string");

    override fun toString() = value
}

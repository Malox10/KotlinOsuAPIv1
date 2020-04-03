package enum

enum class RateLimit(val value: Long) {
    Standard(60),
    Approved(1200);
}
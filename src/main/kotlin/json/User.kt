package json

data class User(
    val user_id: Int,
    val username: String,
    val join_date: String,
    val count300: Long,
    val count100: Long,
    val count50: Long,
    val playcount: Int,
    val ranked_score: Long,
    val total_score: Long,
    val pp_rank: Int,
    val level: Float,
    val pp_raw: Float,
    val accuracy: Float,
    val count_rank_ss: Int,
    val count_rank_ssh: Int,
    val count_rank_s: Int,
    val count_rank_sh: Int,
    val count_rank_a: Int,
    val country: String,
    val total_seconds_played: Int,
    val pp_country_rank: Int,
    val events: List<Event>
) {
    data class Event(
        val display_html: String,
        val beatmap_id: Long,
        val beatmapset_id: Long,
        val date: String,
        val epicfactor: Int
    )
}

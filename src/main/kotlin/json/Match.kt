package json

data class Match(
    val match: MatchHeader,
    val games: List<Game>
) {
    data class MatchHeader(
        val match_id: Long,
        val name: String,
        val start_time: String,
        val end_time: String
    )

    data class Game(
        val game_id: Long,
        val start_time: String,
        val end_time: String,
        val beatmap_id: Long,
        val play_mode: Byte,
        val match_type: String, //undocumented
        val scoring_type: Byte,
        val team_type: Byte,
        val mods: Long,
        val scores: List<Score>
    ) {
        data class Score(
            val slot: Byte,
            val team: Byte,
            val user_id: Long,
            val score: Long,
            val maxcombo: Int,
            val rank: String, //unused
            val count50: Int,
            val count100: Int,
            val count300: Int,
            val countgeki: Int,
            val countkatu: Int,
            val perfect: Byte,
            val pass: Byte,
            val enabled_mods: Long?
        )
    }
}

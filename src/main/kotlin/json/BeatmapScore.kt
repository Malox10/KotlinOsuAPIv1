package json

data class BeatmapScore(
    val score_id: Long,
    val score: Long,
    val username: String,
    val count300: Long,
    val count100: Long,
    val count50: Long,
    val countmiss: Long,
    val maxcombo: Long,
    val countkatu: Long,
    val countgeki: Long,
    val perfect: Byte,
    val enabled_mods: Long,
    val user_id: Long,
    val date: String,
    val rank: Long,
    val pp: Float,
    val replay_available: Byte
)

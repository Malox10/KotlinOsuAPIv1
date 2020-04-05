package json

data class UserScore(
    val beatmap_id: Long,
    val score_id: Long,
    val score: Long,
    val maxcombo: Long,
    val count50: Long,
    val count100: Long,
    val count300: Long,
    val countmiss: Long,
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
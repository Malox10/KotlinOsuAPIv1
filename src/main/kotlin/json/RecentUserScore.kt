package json

data class RecentUserScore (
   val beatmap_id: Long,
   val score: Long,
   val maxcombo: Long,
   val count50: Long,
   val count100: Long,
   val count300: Long,
   val countmiss: Long,
   val countkatu: Long,
   val countgeki: Long,
   val perfect: Long,
   val enabled_mods: Long,
   val user_id: Long,
   val date: String,
   val rank: String
)

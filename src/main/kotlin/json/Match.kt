package json

import com.google.gson.TypeAdapter
import com.google.gson.stream.*
import java.io.IOException

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

class MatchHeaderTypeAdapter : TypeAdapter<Match.MatchHeader>() {

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): Match.MatchHeader? {
        val token = jsonReader.peek()

        if(token == JsonToken.BEGIN_OBJECT) {
            var matchId: Long = 0
            lateinit var name: String
            lateinit var startTime: String
            lateinit var endTime: String

            jsonReader.beginObject()
            while (jsonReader.hasNext()) {
                when (jsonReader.nextName()) {
                    "match_id" -> matchId = jsonReader.nextLong()
                    "name" -> name = jsonReader.nextString()
                    "start_time" -> startTime = jsonReader.nextString()
                    "end_time" -> endTime = jsonReader.nextString()
                }
            }
            jsonReader.endObject()
            return Match.MatchHeader(matchId, name, startTime, endTime)
        }
        else {
            //to parse: {"match":0,"games":[]}
            if(jsonReader.peek() == JsonToken.NUMBER ) {
                val x = jsonReader.nextInt()
                if(x == 0) return null
            }
            throw Exception("unexpected value for Matchheader")
        }
    }

    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, match: Match.MatchHeader) {
        //Might implement later for completion
        jsonWriter.beginObject()
        jsonWriter.endObject()
    }
}

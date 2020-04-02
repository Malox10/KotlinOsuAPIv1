import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ParametersBuilder
import io.ktor.http.URLBuilder
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import enum.*
import json.*

class OsuAPI(private val key: String) {
    private val gson: Gson
    private val client = HttpClient()

    init {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(Match.MatchHeader::class.java, MatchHeaderTypeAdapter())
        gson = gsonBuilder.create()
    }

    suspend fun getBeatmaps(
        since: String? = null,
        beatmapsetID: Long? = null,
        beatmapID: Long? = null,
        user: String? = null,
        usertype: UserType? = null,
        mode: Mode? = null,
        includeConverts: Converts? = null,
        beatmapHash: String? = null,
        limit: Int? = null,
        mods: Long? = null
    ): List<Beatmap> {
        with(getOsuUrlBuilder()) {
            encodedPath = "get_beatmaps"
            parameters.run {
                addIfNotNull("since", since)
                addIfNotNull("s", beatmapsetID)
                addIfNotNull("b", beatmapID)
                addIfNotNull("u", user)
                addIfNotNull("type", usertype)
                addIfNotNull("m", mode)
                addIfNotNull("a", includeConverts)
                addIfNotNull("h", beatmapHash)
                addIfNotNull("limit", limit)
                addIfNotNull("mods", mods)
            }
            RateLimiter.grantAccess()
            val jsonString = client.get<String>(buildString())
            return gson.fromJson<List<Beatmap>>(jsonString, beatmapListType)
        }
    }

    suspend fun getUser(
        user: String? = null,
        usertype: UserType? = null,
        mode: Mode? = null,
        event_days: Byte? = null
    ): List<User> {
        with(getOsuUrlBuilder()) {
            encodedPath = "get_user"
            parameters.apply {
                addIfNotNull("u", user)
                addIfNotNull("type", usertype)
                addIfNotNull("m", mode)
                addIfNotNull("event_days", event_days)
            }
            RateLimiter.grantAccess()
            val jsonString = client.get<String>(buildString())
            return gson.fromJson(jsonString, userListType)
        }
    }

    suspend fun getScores(
        beatmapID: Long? = null,
        user: String? = null,
        usertype: UserType? = null,
        mode: Mode? = null,
        limit: Int? = null
    ): List<Score> {
        with(getOsuUrlBuilder()) {
            encodedPath = "get_scores"
            parameters.run {

            }
            val jsonString = client.get<String>(buildString())
            return gson.fromJson(jsonString, scoreListType)
        }
    }

    suspend fun getUserBest() {
        with(getOsuUrlBuilder()) {
            encodedPath = "get_scores"
            parameters.run {

            }
            return client.get(buildString())
        }
    }

    suspend fun getUserRecent() {
        with(getOsuUrlBuilder()) {
            encodedPath = "get_scores"
            parameters.run {

            }
            return client.get(buildString())
        }
    }

    suspend fun getMatch(matchID: Long): Match {
        with(getOsuUrlBuilder()) {
            encodedPath = "get_match"
            parameters.addIfNotNull("mp", matchID)
            return client.get(buildString())
        }
    }

    fun modBuilder(vararg mods: Mods): Long {
        val modset = mods.toMutableSet()
        if (mods.contains(Mods.Nightcore) && !(mods.contains(Mods.DoubleTime))) modset.add(Mods.DoubleTime)
        if (mods.contains(Mods.Perfect) && !(mods.contains(Mods.SuddenDeath))) modset.add(Mods.SuddenDeath)
        var bitwise: Long = 0
        for (mod in modset) bitwise += mod.value
        return bitwise
    }

    private fun getOsuUrlBuilder(): URLBuilder {
        return URLBuilder().apply {
            host = "osu.ppy.sh/api"
            parameters["k"] = key
        }
    }

    private fun ParametersBuilder.addIfNotNull(name: String, value: Any?) {
        if (value != null) this[name] = value.toString()
    }

    private object RateLimiter {
        private val queue: MutableList<Long> = mutableListOf()
        private const val rateLimitAmount: Long = 1200  //1200 requests per minute
        private const val rateLimitWindowMillis: Long = 60000
        private val mutex: Mutex = Mutex()

        suspend fun grantAccess() {
            mutex.withLock {
                while (queue.size >= rateLimitAmount) {
                    if (queue.first() < System.currentTimeMillis()) {
                        queue.removeAt(0)
                        break
                    } else delay(1000)
                }
                queue.add(System.currentTimeMillis() + rateLimitWindowMillis)
            }
            return
        }
    }

    companion object{
        private val beatmapListType = object : TypeToken<List<Beatmap>>() {}.type
        private val userListType = object : TypeToken<List<User>>() {}.type
        private val scoreListType = object : TypeToken<List<Score>>() {}.type
    }
}

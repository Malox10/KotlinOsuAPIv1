# Osu API v1 (Work In Progress)

An unofficial async Kotlin API Wrapper for [osu!](https://osu.ppy.sh/home). You can find further API documentation [here](https://github.com/ppy/osu-api/wiki)

A basic example of using the library
```kotlin
import enum.*

suspend fun main() {
    val API = OsuAPI("your api-key here")

    val match = API.getMatch(matchID = 59472785L)

    val beatmaps = API.getBeatmaps(
        user = "Malox",
        usertype = UserType.Name,
        includeConverts = Converts.Include,
        mode = Mode.Mania,
        mods = OsuAPI.modBuilder(
            Mods.HalfTime,
            Mods.HardRock,
            Mods.Mirror
        ),
        limit = 42
    )
}
```

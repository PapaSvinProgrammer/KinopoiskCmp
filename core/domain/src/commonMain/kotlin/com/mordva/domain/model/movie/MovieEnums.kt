package com.mordva.domain.model.movie

enum class MovieType(val apiValue: String) {
    MOVIE("movie"),
    TV_SERIES("tv-series"),
    CARTOON("cartoon"),
    ANIME("anime"),
    ANIMATED_SERIES("animated-series");
}

enum class MovieStatus(val apiValue: String) {
    ANNOUNCED("announced"),
    COMPLETED("completed"),
    FILMING("filming"),
    POST_PRODUCTION("post-production"),
    PRE_PRODUCTION("pre-production");
}

enum class RatingMpaa(val apiValue: String) {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");
}

package com.pluu.webtoon.data

typealias WeeklyRequest = Int

data class EpisodeRequest(
    val toonId: String,
    val page: Int
)

class DetailRequest(
    val toonId: String,
    val episodeId: String,
    var episodeTitle: String
)

package com.eyegym.app.domain.model

import com.eyegym.app.R

data class Trip(
    val id: Int,
    val nameInt: Int,
    val shortInt: Int,
    val contentInt: Int,
    val imageInt: Int,
    val group: Int,
    val isFavorite: Boolean = false
)


val mockTrips = listOf(
    Trip(
        id = 1,
        nameInt = R.string.eat_title_1,
        shortInt = R.string.eat_short_1,
        contentInt = R.string.eat_content_1,
        imageInt = R.drawable.eat_1,
        group = R.string.eat
    ),
    Trip(
        id = 2,
        nameInt = R.string.eat_title_2,
        shortInt = R.string.eat_short_2,
        contentInt = R.string.eat_content_2,
        imageInt = R.drawable.eat_2,
        group = R.string.eat
    ),
    Trip(
        id = 3,
        nameInt = R.string.eat_title_3,
        shortInt = R.string.eat_short_3,
        contentInt = R.string.eat_content_3,
        imageInt = R.drawable.eat_3,
        group = R.string.eat
    ),
    Trip(
        id = 4,
        nameInt = R.string.eat_title_4,
        shortInt = R.string.eat_short_4,
        contentInt = R.string.eat_content_4,
        imageInt = R.drawable.eat_4,
        group = R.string.eat
    ),
    Trip(
        id = 5,
        nameInt = R.string.eat_title_5,
        shortInt = R.string.eat_short_5,
        contentInt = R.string.eat_content_5,
        imageInt = R.drawable.eat_5,
        group = R.string.eat
    ),
    Trip(
        id = 6,
        nameInt = R.string.trips_title_1,
        shortInt = R.string.trips_short_1,
        contentInt = R.string.trips_content_1,
        imageInt = R.drawable.trips_1,
        group = R.string.trips
    ),
    Trip(
        id = 7,
        nameInt = R.string.trips_title_2,
        shortInt = R.string.trips_short_2,
        contentInt = R.string.trips_content_2,
        imageInt = R.drawable.trips_2,
        group = R.string.trips
    ),
    Trip(
        id = 8,
        nameInt = R.string.trips_title_3,
        shortInt = R.string.trips_short_3,
        contentInt = R.string.trips_content_3,
        imageInt = R.drawable.trips_3,
        group = R.string.trips
    ),
    Trip(
        id = 9,
        nameInt = R.string.trips_title_4,
        shortInt = R.string.trips_short_4,
        contentInt = R.string.trips_content_4,
        imageInt = R.drawable.trips_4,
        group = R.string.trips
    ),
    Trip(
        id = 10,
        nameInt = R.string.trips_title_5,
        shortInt = R.string.trips_short_5,
        contentInt = R.string.trips_content_5,
        imageInt = R.drawable.trips_5,
        group = R.string.trips
    ),
    Trip(
        id = 11,
        nameInt = R.string.vision_hygiene_title_1,
        shortInt = R.string.vision_hygiene_short_1,
        contentInt = R.string.vision_hygiene_content_1,
        imageInt = R.drawable.vision_hygiene_1,
        group = R.string.vision_hygiene
    ),
    Trip(
        id = 12,
        nameInt = R.string.vision_hygiene_title_2,
        shortInt = R.string.vision_hygiene_short_2,
        contentInt = R.string.vision_hygiene_content_2,
        imageInt = R.drawable.vision_hygiene_2,
        group = R.string.vision_hygiene
    ),
    Trip(
        id = 13,
        nameInt = R.string.vision_hygiene_title_3,
        shortInt = R.string.vision_hygiene_short_3,
        contentInt = R.string.vision_hygiene_content_3,
        imageInt = R.drawable.vision_hygiene_3,
        group = R.string.vision_hygiene
    ),
    Trip(
        id = 14,
        nameInt = R.string.vision_hygiene_title_4,
        shortInt = R.string.vision_hygiene_short_4,
        contentInt = R.string.vision_hygiene_content_4,
        imageInt = R.drawable.vision_hygiene_4,
        group = R.string.vision_hygiene
    ),
    Trip(
        id = 15,
        nameInt = R.string.vision_hygiene_title_5,
        shortInt = R.string.vision_hygiene_short_5,
        contentInt = R.string.vision_hygiene_content_5,
        imageInt = R.drawable.vision_hygiene_5,
        group = R.string.vision_hygiene
    ),
)
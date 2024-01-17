package com.javichaques.core.model

import java.time.ZonedDateTime

object Mocks {
    val user =
        UserDO(
            gender = "male",
            name =
                NameDO(
                    title = "Mr",
                    first = "Samuel",
                    last = "Davies",
                ),
            location =
                LocationDO(
                    street =
                        StreetDO(
                            number = 2181,
                            name = "Ward Street",
                        ),
                    city = "Tauranga",
                    state = "Nelson",
                    country = "New Zealand",
                    postcode = "55372",
                    timezone =
                        TimezoneDO(
                            offset = "-9:00",
                            description = "Alaska",
                        ),
                    coordinates =
                        CoordinatesDO(
                            latitude = 55.1645,
                            longitude = -100.2445,
                        ),
                ),
            id =
                IdDO(
                    name = "",
                    value = null,
                ),
            phone = "(998)-005-7931",
            cell = "(436)-243-3904",
            dob =
                DobDO(
                    date = ZonedDateTime.now(),
                    age = 5,
                ),
            nat = "NZ",
            email = "samuel.davies@example.com",
            login =
                LoginDO(
                    uuid = "223fc78-3c85-4999-a10e-0ce7ffe118bf",
                    salt = "nVFuMLKy",
                    sha1 = "b8847fd6a24d8d42aa6d8168e2690d7ebd3fb764",
                    md5 = "eed8d0dc6a258e694eb9f49359086ede",
                    sha256 = "33e87ae1a12c27e17357bfa2ab434ad3c479da2c3928905cfa751d5f5479cdda",
                    password = "alexandr",
                    username = "angrytiger104",
                ),
            picture =
                PictureDO(
                    large = "https://randomuser.me/api/portraits/men/49.jpg",
                    medium = "https://randomuser.me/api/portraits/med/men/49.jpg",
                    thumbnail = "https://randomuser.me/api/portraits/thumb/men/49.jpg",
                ),
            registered =
                RegisteredDO(
                    date = ZonedDateTime.now(),
                    age = 1,
                ),
        )
}

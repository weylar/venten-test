package com.idris.aminu.android.view.carOwnerList

import com.idris.aminu.android.models.CarOwner

object FakeData {

    private val car1 = CarOwner(
        1, 2, "Scot", "Hainning", "shainning0@so-net.ne.jp",
        "Thailand", "Lincoln",
        "1996", "Maroon", "Male",
        "Staff Accountant III", "Cras mi pede" +
                "malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, " +
                "consectetuer adipiscing elit. Proin interdum mauris non ligula pellentesque ultrices."
    )

    private val car2 = CarOwner(
        2, 2, "	Vannie", "Fitzer",
        "vfitzer1@samsung.com", "France", "Chrysler", "2005", "Green",
        "Female", "VP Quality Control",
        "Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at" +
                " velit. Vivamus vel nulla eget eros elementum pellentesque."
    )

    private val car3 = CarOwner(
        3,
        3,
        "Sissy",
        "Willbourne",
        "swillbourne2@xinhuanet.com",
        "Bolivia",
        "Lexus",
        "2004",
        "Puce",
        "Female",
        "Staff Accountant",
        "I	Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, " +
                "condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. " +
                "Cras pellentesque volutpat dui."
    )


    val data = arrayListOf(car1, car2, car3)
}
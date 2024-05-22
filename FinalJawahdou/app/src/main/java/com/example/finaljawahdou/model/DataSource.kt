package com.example.finaljawahdou.model

import com.example.finaljawahdou.R

class DataSource () {
    fun loadClubs(): List<Club> {
        return listOf<Club>(
            Club(
                imgRes = R.drawable.zero,
                nameRes = R.string.club1,
                descripRes = R.string.description1
            ),
            Club(
                imgRes = R.drawable.act,
                nameRes = R.string.club2,
                descripRes = R.string.description2
            ),Club(
                imgRes = R.drawable.atlas,
                nameRes = R.string.club3,
                descripRes = R.string.description3
            ),Club(
                imgRes = R.drawable.enactus,
                nameRes = R.string.club4,
                descripRes = R.string.description4
            ),Club(
                imgRes = R.drawable.jci,
                nameRes = R.string.club5,
                descripRes = R.string.description5
            ),Club(
                imgRes = R.drawable.merit,
                nameRes = R.string.club6,
                descripRes = R.string.description6
            ),Club(
                imgRes = R.drawable.je,
                nameRes = R.string.club7,
                descripRes = R.string.description7
            ),Club(
                imgRes = R.drawable.startup,
                nameRes = R.string.club8,
                descripRes = R.string.description8
            ),Club(
                imgRes = R.drawable.timun,
                nameRes = R.string.club9,
                descripRes = R.string.description9
            ),Club(
                imgRes = R.drawable.tuniact,
                nameRes = R.string.club10,
                descripRes = R.string.description10
            ),
        )
    }
}

package com.idris.aminu.android.view.carOwnerList

import android.os.Environment
import com.idris.aminu.android.R
import com.idris.aminu.android.models.CarOwner
import com.idris.aminu.android.models.CarOwnerList
import com.idris.aminu.android.models.FilterElement
import com.idris.aminu.android.util.Utility
import com.idris.aminu.android.util.Utility.CSVHeader.BIO
import com.idris.aminu.android.util.Utility.CSVHeader.CAR_COLOR
import com.idris.aminu.android.util.Utility.CSVHeader.CAR_MODEL
import com.idris.aminu.android.util.Utility.CSVHeader.COUNTRY
import com.idris.aminu.android.util.Utility.CSVHeader.EMAIL
import com.idris.aminu.android.util.Utility.CSVHeader.FIRST_NAME
import com.idris.aminu.android.util.Utility.CSVHeader.GENDER
import com.idris.aminu.android.util.Utility.CSVHeader.ID
import com.idris.aminu.android.util.Utility.CSVHeader.JOB_TITLE
import com.idris.aminu.android.util.Utility.CSVHeader.LAST_NAME
import com.idris.aminu.android.util.Utility.CSVHeader.YEAR
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.FileReader
import kotlin.math.nextTowards
import kotlin.math.roundToInt


class FilterManager(private val absoluteFile: File) {


    /*Performing a IO operations will take time,
       so it's safe to take it out of  the UI thread*/

    suspend fun filter(criteria: FilterElement): CarOwnerList {


        val reader = CSVReader(FileReader(absoluteFile.absolutePath))
        val result = CarOwnerList()
        withContext(Dispatchers.IO) {
            try {
                val random = Math.random()
                val list = listOf(R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4, R.drawable.car5)
                while (reader.readNext() != null) {

                    if (reader.readNext()[YEAR].toLong() in criteria.start_year..criteria.end_year
                        /*&& criteria.gender == reader.readNext()[GENDER].toString()
                        && criteria.colors.any { it == reader.readNext()[CAR_COLOR].toString() }
                        && criteria.countries.any { it == reader.readNext()[GENDER].toString() }*/
                    ) {


                        result.add(
                            CarOwner(
                                reader.readNext()[ID].toLong(),
                                list[(random.nextTowards(4.0)).roundToInt()],
                                reader.readNext()[FIRST_NAME],
                                reader.readNext()[LAST_NAME],
                                reader.readNext()[EMAIL],
                                reader.readNext()[COUNTRY],
                                reader.readNext()[CAR_MODEL],
                                reader.readNext()[YEAR],
                                reader.readNext()[CAR_COLOR],
                                reader.readNext()[GENDER],
                                reader.readNext()[JOB_TITLE],
                                reader.readNext()[BIO]
                            )
                        )
                    }


                }
            } catch (e: Exception) {
                e.printStackTrace()

            }

        }

        return result
    }


}
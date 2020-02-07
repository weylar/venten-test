package com.idris.aminu.android.view.carOwnerList

import android.annotation.SuppressLint
import com.idris.aminu.android.R
import com.idris.aminu.android.models.CarOwner
import com.idris.aminu.android.models.CarOwnerList
import com.idris.aminu.android.models.FilterElement
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
import de.siegmar.fastcsv.reader.CsvReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class FilterManager(private val absoluteFile: File) {


    /*Performing a IO operations will take time,
       so it's safe to take it out of  the UI thread*/

    @SuppressLint("DefaultLocale")
    suspend fun filter(criteria: FilterElement): CarOwnerList {

        val result = CarOwnerList()
        withContext(Dispatchers.IO) {
            try {
                val csvReader = CsvReader()
                csvReader.setFieldSeparator(',')
                csvReader.setSkipEmptyRows(true)
                csvReader.setContainsHeader(true)
                csvReader.parse(BufferedReader(FileReader(absoluteFile.absolutePath)))
                    .use { csvParser ->
                        while (true) {
                            val row = csvParser.nextRow() ?: break
                            //Filter through the values by the respective criteria
                            if (row.getField(YEAR).toLong() in criteria.start_year..criteria.end_year) {
                                if ((criteria.gender.capitalize() == row.getField(GENDER).toString().capitalize())
                                    or (criteria.gender.isEmpty())
                                ) {
                                    if ((row.getField(COUNTRY).capitalize() in criteria.countries.map { it.capitalize() })
                                        or criteria.countries.isEmpty()
                                    ) {
                                        if ((row.getField(CAR_COLOR).capitalize() in criteria.colors.map { it.capitalize() })
                                            or criteria.colors.isEmpty()
                                        ) {
                                            result.add(
                                                CarOwner(
                                                    row.getField(ID).toLong(),
                                                    R.drawable.car1,
                                                    row.getField(FIRST_NAME),
                                                    row.getField(LAST_NAME),
                                                    row.getField(EMAIL),
                                                    row.getField(COUNTRY),
                                                    row.getField(CAR_MODEL),
                                                    row.getField(YEAR),
                                                    row.getField(CAR_COLOR),
                                                    row.getField(GENDER),
                                                    row.getField(JOB_TITLE),
                                                    row.getField(BIO)
                                                )
                                            )
                                        }
                                    }
                                }
                            }

                        }
                    }

            } catch (e: Exception) {
                Timber.e(e.printStackTrace().toString())

            }

        }

        return result
    }


}
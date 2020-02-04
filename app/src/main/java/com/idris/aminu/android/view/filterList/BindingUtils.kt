/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.idris.aminu.android.view.filterList

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.idris.aminu.android.models.Filter
import com.idris.aminu.android.models.FilterElement

//@BindingAdapter("sleepImage")
//fun ImageView.setSleepImage(item: SleepNight?) {
//    item?.let {
//        setImageResource(when (item.sleepQuality) {
//            0 -> R.drawable.ic_sleep_0
//            1 -> R.drawable.ic_sleep_1
//            2 -> R.drawable.ic_sleep_2
//            3 -> R.drawable.ic_sleep_3
//            4 -> R.drawable.ic_sleep_4
//            5 -> R.drawable.ic_sleep_5
//            else -> R.drawable.ic_sleep_active
//        })
//    }
//}
//
@BindingAdapter("years")
fun TextView.setYearsDiff(item: FilterElement?) {
    item?.let {
        text = "${item.startYear} - ${item.endYear}"
    }
}

@BindingAdapter("gender")
fun TextView.setGender(item: FilterElement?) {
    item?.let {
        text = item.gender
    }
}

@BindingAdapter("countries")
fun TextView.setCountries(item: FilterElement?) {
    item?.let {
        item.countries.map {
            append("$it ")
        }
    }
}

@BindingAdapter("colors")
fun TextView.setColors(item: FilterElement?) {
    item?.let {
        item.colors.map {
            append("$it ")
        }
    }
}

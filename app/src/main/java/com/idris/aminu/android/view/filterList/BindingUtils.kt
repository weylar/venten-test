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

import android.graphics.PorterDuff
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.databinding.BindingAdapter
import com.idris.aminu.android.R
import com.idris.aminu.android.models.FilterElement
import com.idris.aminu.android.util.Color
import timber.log.Timber


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
        text = "${item.start_year} - ${item.end_year}"
    }
}

@BindingAdapter("gender")
fun TextView.setGender(item: FilterElement?) {
    item?.let {
        val res = item.gender
        text = when (res) {
            "male" -> {
                context.getString(R.string.male)
            }
            "female" -> {
                context.getString(R.string.female)
            }
            else -> {
                context.getString(R.string.all_gender)
            }
        }
    }

}

@BindingAdapter("countries")
fun LinearLayout.setCountries(item: FilterElement?) {
    item?.let { element ->
        if (element.countries.isEmpty()) {
            val childLayout = buildChildLayout()
            val view = buildTextView("All countries")
            childLayout.addView(view)
            addView(childLayout)
        } else {
            element.countries.map { country ->
                val childLayout = buildChildLayout()
                val view = buildTextView(country)
                childLayout.addView(view)
                addView(childLayout)
            }
        }
    }
}

@BindingAdapter("colors")
fun LinearLayout.setColors(item: FilterElement?) {

    item?.let { element ->
        if (element.colors.isEmpty()) {
            val childLayout = buildChildLayout()
            val view = buildTextView("All colors")
            childLayout.addView(view)
            addView(childLayout)
        } else {
            element.colors.map { color ->
                val childLayout = buildChildLayout()
                val view = buildColor(color)
                childLayout.addView(view)
                addView(childLayout)
            }

        }
    }
}

private fun LinearLayout.buildColor(color: String): TextView {
    val view = TextView(this.context)
    val layoutParams = LinearLayout.LayoutParams(64, 64)
    layoutParams.setMargins(8)
    view.layoutParams = layoutParams
    val drawable = ContextCompat.getDrawable(context, R.drawable.color_bg)
    drawable?.mutate()?.setColorFilter(
        when (color) {
            Color.RED.color -> ContextCompat.getColor(context, R.color.red)
            Color.GREEN.color -> ContextCompat.getColor(context, R.color.green)
            Color.VIOLET.color -> ContextCompat.getColor(context, R.color.violet)
            Color.YELLOW.color -> ContextCompat.getColor(context, R.color.yellow)
            Color.BLUE.color -> ContextCompat.getColor(context, R.color.blue)
            Color.TEAL.color -> ContextCompat.getColor(context, R.color.teal)
            Color.MAROON.color -> ContextCompat.getColor(context, R.color.maroon)
            Color.AQUAMARINE.color -> ContextCompat.getColor(
                context,
                R.color.aquamarine
            )
            Color.ORANGE.color -> ContextCompat.getColor(context, R.color.orange)
            Color.MAUV.color -> ContextCompat.getColor(context, R.color.mauv)
            else -> ContextCompat.getColor(context, R.color.white)
        }, PorterDuff.Mode.SRC_IN
    )

    view.background = drawable
    return view
}


private fun LinearLayout.buildChildLayout(): LinearLayout {
    val childLayout = LinearLayout(context)
    val linearParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    childLayout.layoutParams = linearParams
    childLayout.gravity = Gravity.CENTER
    return childLayout
}

private fun LinearLayout.buildTextView(country: String): TextView {
    val view = TextView(this.context)
    val layoutParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    view.text = country
    view.setTextColor(ContextCompat.getColor(context, R.color.black))
    view.setPadding(32, 16,32, 16)
    layoutParams.setMargins(8)
    view.layoutParams = layoutParams
    val drawable = ContextCompat.getDrawable(context, R.drawable.country_bg)
    view.background = drawable
    return view
}


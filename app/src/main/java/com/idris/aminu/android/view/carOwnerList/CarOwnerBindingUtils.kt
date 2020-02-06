package com.idris.aminu.android.view.carOwnerList

import android.graphics.PorterDuff
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.databinding.BindingAdapter
import com.idris.aminu.android.R
import com.idris.aminu.android.models.CarOwner
import com.idris.aminu.android.models.FilterElement
import com.idris.aminu.android.util.Color
import timber.log.Timber
import kotlin.math.nextTowards
import kotlin.math.nextUp


@BindingAdapter("image")
fun ImageView.setImage(item: CarOwner?) {

    item?.let {
        setImageResource(item.image)
    }
}

@BindingAdapter("name")
fun TextView.setName(item: CarOwner?) {
    item?.let {
        text = "${item.firstName}  ${item.lastName}"
    }

}

@BindingAdapter("make")
fun TextView.setMake(item: CarOwner?) {
    item?.let {
        text = item.carModel
    }
}

@BindingAdapter("year")
fun TextView.setYear(item: CarOwner?) {
    item?.let {
        text = item.year
    }
}

@BindingAdapter("color")
fun View.setColor(item: CarOwner?) {
    item?.let {car ->
        val drawable = ContextCompat.getDrawable(context, R.drawable.color_bg)
        drawable?.mutate()?.setColorFilter(
            when (car.carColor) {
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
                else -> ContextCompat.getColor(context, R.color.black)
            }, PorterDuff.Mode.SRC_IN
        )

       background = drawable
    }
}

@BindingAdapter("country")
fun TextView.setCountry(item: CarOwner?) {
    item?.let {
        text = item.country
    }
}

@BindingAdapter("email")
fun TextView.setEmail(item: CarOwner?) {
    item?.let {
        text = item.email
    }
}

@BindingAdapter("job")
fun TextView.setJob(item: CarOwner?) {
    item?.let {
        text = item.jobTitle
    }
}

@BindingAdapter("bio")
fun TextView.setBio(item: CarOwner?) {
    item?.let {
        text = item.bio
    }
}

@BindingAdapter("gender")
fun TextView.setGender(item: CarOwner?) {
    item?.let {
        text = it.gender
        }


}


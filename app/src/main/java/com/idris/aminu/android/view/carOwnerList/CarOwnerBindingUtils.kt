package com.idris.aminu.android.view.carOwnerList

import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.idris.aminu.android.R
import com.idris.aminu.android.models.CarOwner
import com.idris.aminu.android.util.Color


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
    item?.let { car ->
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
                Color.PUCE.color -> ContextCompat.getColor(context, R.color.puce)
                Color.INDIGO.color -> ContextCompat.getColor(context, R.color.indigo)
                Color.TURQUOISE.color -> ContextCompat.getColor(context, R.color.turquoise)
                Color.GOLDENROD.color -> ContextCompat.getColor(context, R.color.goldenrod)
                Color.FUSCIA.color -> ContextCompat.getColor(context, R.color.fushcia)
                Color.PINK.color -> ContextCompat.getColor(context, R.color.pink)
                Color.CRIMSON.color -> ContextCompat.getColor(context, R.color.crimson)
                Color.KHAKI.color -> ContextCompat.getColor(context, R.color.khaki)
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


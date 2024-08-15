package com.example.mycontacts.domain.model

import androidx.annotation.DrawableRes
import com.example.mycontacts.R

enum class Gender(@DrawableRes val icon: Int, val type: String) {
    MALE(R.drawable.ic_male, "Male"),
    FEMALE(R.drawable.ic_female, "Female"),
    OTHER(R.drawable.ic_other_gender,"Other")
}

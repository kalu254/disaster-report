package com.kalu.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.Flow

data class User (
    val id : String,
    val f_name : String,
    val l_name : String,
    val email_address : String,
    val location : String,
    val phone_number : Int,
    val password : String,
)

@Parcelize
data class Disaster(
    val disaster_id: String,
    val date_reported: String,
    val disaster_type: String,
    val disaster_description: String,
    val village: String,
    val disaster_img_url_one: String,
    val disaster_img_url_two: String,
    val disaster_img_url_three: String,
    val disaster_img_url_four: String,
) : Parcelable

data class UserAuthorization(
    val username: String,
    val password: String
)

data class LoginResponse(
    val accessToken: String,
    val email: String,
    val id: Int,
    val roles: List<String>,
    val tokenType: String,
    val username: String
)

data class ImageItem(val imageUrl : String)

data class UserProfile(val id: Int?, val username: String?, val email_address: String?)

package com.example.hugoanjos.helpers

import android.content.SharedPreferences
import android.os.SystemClock
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import org.json.JSONObject


inline fun <reified T> Gson.fromJson(json: String): T? =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)


fun SharedPreferences.remove(key: String) =
    edit().remove(key).apply()

inline fun <reified T> nameFromType() = "by_type:${T::class.qualifiedName}"

inline fun <reified T> SharedPreferences.remove() = remove(nameFromType<T>())


fun String.getErrorMessageFromJson(): String {
    return try {
        val jsonObject = JSONObject(this)
        jsonObject.getString("error_description")
    } catch (e: JSONException) {
        ""
    }
}


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

var LAST_NAV_TIME = 0L
private const val MIN_NAV_INTERVAL = 600

fun Fragment.navigate(action: NavDirections, navOptions: NavOptions? = null) {
    if (SystemClock.elapsedRealtime() - LAST_NAV_TIME < MIN_NAV_INTERVAL) {
        return
    }
    LAST_NAV_TIME = SystemClock.elapsedRealtime()
    findNavController().navigate(action, navOptions)
}
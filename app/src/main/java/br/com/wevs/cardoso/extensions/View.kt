package br.com.wevs.cardoso.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun View.visible(visible: Boolean = false, invisible: Boolean = false) {
    visibility = when {
        visible -> View.VISIBLE
        invisible -> View.INVISIBLE
        else -> View.GONE
    }
}
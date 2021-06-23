package br.com.wevs.cardoso.extensions

import android.view.View
import android.widget.ProgressBar
import br.com.wevs.cardoso.R
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_java.view.*

fun String?.loadImage(
    image: ShapeableImageView,
    imagemDeErro: Int = R.drawable.ic_launcher_foreground,
    progressBar: ProgressBar
) {
    Picasso.get()
        .load(this)
        .error(imagemDeErro)
        .into(image, object : Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                progressBar.visibility = View.GONE
            }
        })
}
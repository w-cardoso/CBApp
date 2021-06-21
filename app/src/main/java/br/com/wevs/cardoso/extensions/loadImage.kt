package br.com.wevs.cardoso.extensions

import android.view.View
import br.com.wevs.cardoso.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_java.view.*

fun View.loadImage(
    endereco: String,
    imagemDeErro: Int = R.drawable.ic_launcher_foreground
) {
    Picasso.get()
        .load(endereco)
        .error(imagemDeErro)
        .into(this.item_pull_request_img, object : Callback {
            override fun onSuccess() {
                this@loadImage.item_pull_request_img_progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                this@loadImage.item_pull_request_img_progressBar.visibility = View.GONE
            }
        })
}
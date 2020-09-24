package khatwa.zilalalrahmaapp.others

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imagePath: String?) {
    if (!imagePath.isNullOrEmpty()) {
        Glide.with(view.context)
                .load(Constants.IMAGE_BASE_URL + "news.jpg")   // todo --> replace with imagePath argument
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }

}
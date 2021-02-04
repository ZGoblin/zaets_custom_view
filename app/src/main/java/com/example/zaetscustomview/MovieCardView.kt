package com.example.zaetscustomview

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.util.rangeTo
import com.example.zaetscustomview.databinding.MovieCardBinding


class MovieCardView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defStyleAttrs: Int = 0) : ConstraintLayout(context, attributeSet, defStyleAttrs){
    private var binding: MovieCardBinding =
        MovieCardBinding.bind(inflate(context, R.layout.movie_card, this))

    init {
        setupAttrs(attributeSet)
    }

    private fun setupAttrs(attributeSet: AttributeSet?) {
        val attrs = context.obtainStyledAttributes(attributeSet, R.styleable.MovieCardView)

        binding.apply {
            ivPoster.setImageResource(attrs.getResourceId(R.styleable.MovieCardView_poster, R.drawable.poster))
            tvName.text = attrs.getText(R.styleable.MovieCardView_movieName)
            tvPrice.text = attrs.getText(R.styleable.MovieCardView_price)
            tvType.text = attrs.getText(R.styleable.MovieCardView_type)
            rbRating.rating = attrs.getFloat(R.styleable.MovieCardView_rating, 5.0f)
        }

        attrs.recycle()
    }

    fun setMovieName(@StringRes text: Int) {
        binding.tvName.text = context.getString(text)
    }

    fun setPoster(@DrawableRes image: Int) {
        binding.ivPoster.setImageResource(image)
    }

    fun setPrice(@StringRes price: Int) {
        binding.tvPrice.setText(price)
    }

    fun setType(@StringRes type: Int) {
        binding.tvType.setText(type)
    }

    fun setRating(rating: Float) {
        binding.rbRating.rating = rating
    }
}
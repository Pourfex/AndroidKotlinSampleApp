package fr.android.androidexercises

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso

class BookItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr) {


    private var title: TextView? = null
    private var price: TextView? = null
    private var cover: ImageView? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        title = findViewById<TextView>(R.id.title)
        price = findViewById<TextView>(R.id.price)
        cover = findViewById<ImageView>(R.id.cover)
    }

    fun bindView(book: Book) {
        title?.text = book.title
        price?.text = book.price

        Picasso.get().load(book.cover).into(cover)
    }
}

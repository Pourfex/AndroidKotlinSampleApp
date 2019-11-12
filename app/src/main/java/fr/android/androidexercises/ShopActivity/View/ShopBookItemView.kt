package fr.android.androidexercises.ShopActivity.View

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import fr.android.androidexercises.Global.Book
import fr.android.androidexercises.R

class ShopBookItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr) {


    private var title: TextView? = null
    private var price: TextView? = null
    private var numberItems: TextView? = null
    private var cover: ImageView? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        title = findViewById<TextView>(R.id.title)
        price = findViewById<TextView>(R.id.price)
        numberItems = findViewById<TextView>(R.id.number_books2)
        cover = findViewById<ImageView>(R.id.cover)


    }

    fun bindView(book: Book, nbItems : Int) {
        title?.text = book.title
        price?.text = book.price
        numberItems?.text = nbItems.toString()

        Picasso.get().load(book.cover).into(cover)


    }
}
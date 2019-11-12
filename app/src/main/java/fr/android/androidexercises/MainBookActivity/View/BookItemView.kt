package fr.android.androidexercises.MainBookActivity.View

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import fr.android.androidexercises.Global.Book
import fr.android.androidexercises.R

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

        val buyButton : Button = findViewById(R.id.buy_button)
        buyButton.setOnClickListener {
             Log.i("bookBuy", "Book is going to be buy "+book.title)
            (context as BookBuyer).userWantToBuyBook(book = book)
        }

        val showMoreButton : Button = findViewById(R.id.show_more_button)
        showMoreButton.setOnClickListener { (context as BookDescriptionHandler).userWantToGetInfoOnBook(book = book)  }
    }

    interface BookBuyer {
        fun userWantToBuyBook(book : Book)
    }

    interface BookDescriptionHandler {
        fun userWantToGetInfoOnBook(book : Book)
    }
}

package fr.android.androidexercises

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity(), BookItemView.BookBuyer {

    private var title: TextView? = null
    private var price: TextView? = null
    private var description: TextView? = null
    private var cover: ImageView? = null
    private var book : Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        book = intent.getParcelableExtra(getString(R.string.detail_intent_key))

        title = findViewById<TextView>(R.id.title_detail)
        price = findViewById<TextView>(R.id.price_details)
        description = findViewById<TextView>(R.id.book_description_details)
        cover = findViewById<ImageView>(R.id.cover_detail)

        title?.text = book?.title
        price?.text = book?.price
        description?.text = book?.synopsis?.get(0)
        Picasso.get().load(book?.cover).into(cover)

        val buyButton : Button = findViewById(R.id.buy_button_details)
        buyButton.setOnClickListener {
            Log.i("bookBuy", "Book is going to be buy "+book?.title)
            this.userWantToBuyBook(book = book!!)
        }
    }

    override fun userWantToBuyBook(book: Book) {
        Toast.makeText(this, "Shop Not Available for " + book.title, Toast.LENGTH_LONG).show()
    }

}
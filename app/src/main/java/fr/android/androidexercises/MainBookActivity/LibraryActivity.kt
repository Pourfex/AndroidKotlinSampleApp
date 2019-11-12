package fr.android.androidexercises.MainBookActivity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.android.androidexercises.Global.Book
import fr.android.androidexercises.BookDetailActivity.BookDetailActivity
import fr.android.androidexercises.MainBookActivity.View.BookAdapter
import fr.android.androidexercises.MainBookActivity.View.BookItemView
import fr.android.androidexercises.R

class LibraryActivity : AppCompatActivity(), LibraryPresenter.BookReceiver, BookItemView.BookBuyer, BookItemView.BookDescriptionHandler {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        LibraryPresenter().init(this, this)
    }

    override fun OnBookReceived(books: List<Book>){
        val listView = findViewById<ListView>(R.id.bookListView)
        listView.adapter = BookAdapter(this, books)
    }

    override fun userWantToBuyBook(book: Book) {
        Toast.makeText(this, "Shop Not Available for " + book.title, Toast.LENGTH_LONG).show()
    }

    override fun userWantToGetInfoOnBook(book: Book) {
        Toast.makeText(this, "Get description for " + book.title, Toast.LENGTH_LONG).show()
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra(getString(R.string.detail_intent_key), book)
        startActivity(intent)
    }
}

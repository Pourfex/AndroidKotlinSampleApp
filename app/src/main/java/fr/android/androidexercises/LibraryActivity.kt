package fr.android.androidexercises

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LibraryActivity : AppCompatActivity(), LibraryPresenter.BookReceiver, BookItemView.BookBuyer, BookItemView.BookDescriptionHandler {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        val presenter = LibraryPresenter()

        presenter.init(this, this)


    }

    override fun OnBookReceived(books: List<Book>){
        val listView = findViewById<ListView>(R.id.bookListView)

        val adapter = BookAdapter(this, books)

        listView.adapter = adapter
    }

    override fun userWantToBuyBook(book: Book) {
        Toast.makeText(this, "Shop Not Available for " + book.title, Toast.LENGTH_LONG).show()
    }

    override fun userWantToGetInfoOnBook(book: Book) {
        Toast.makeText(this, "Get description for " + book.title, Toast.LENGTH_LONG).show()
    }
}

package fr.android.androidexercises

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class LibraryActivity : AppCompatActivity(), LibraryPresenter.BookReceiver {


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

    override fun GetBooks(books: List<Book>){

    }
}

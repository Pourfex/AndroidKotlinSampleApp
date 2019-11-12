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
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar




//Main activity. Work with a presenter to get data from and a ListView to display them (adapter). Responsible of navigation
class LibraryActivity : AppCompatActivity(), LibraryPresenter.BookReceiver, BookItemView.BookBuyer, BookItemView.BookDescriptionHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)
        LibraryPresenter().init(this, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_library, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_home -> {
                Toast.makeText(this, "Already at home", Toast.LENGTH_SHORT).show()
            }
            R.id.action_shop -> {
                Toast.makeText(this, "Shop Not Available", Toast.LENGTH_LONG).show()
            }
        }
        return true
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

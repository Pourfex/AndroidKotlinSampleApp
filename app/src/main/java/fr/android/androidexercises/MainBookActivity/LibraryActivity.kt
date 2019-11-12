package fr.android.androidexercises.MainBookActivity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
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
import android.widget.TextView
import androidx.core.view.MenuItemCompat
import fr.android.androidexercises.ShopActivity.ShopActivity

//Main activity. Work with a presenter to get data from and a ListView to display them (adapter). Responsible of navigation
class LibraryActivity : AppCompatActivity(), LibraryPresenter.BookReceiver, BookItemView.BookBuyer, BookItemView.BookDescriptionHandler {

    var textCartItemCount: TextView? = null
    private var shoppingNumbers : Int = 0

    private var PRIVATE_MODE = 0
    private val PREF_NAME = "library_cart"
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)

        LibraryPresenter().init(this, this)

        if(savedInstanceState != null){
            shoppingNumbers = savedInstanceState.getInt(getString(R.string.bought_items))
        }

        Log.i("shopping number", shoppingNumbers.toString())

        sharedPref = getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        Log.i("sharedPrefs", sharedPref.getInt("c8fabf68-8374-48fe-a7ea-a00ccd07afff", 666).toString())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_library, menu)

        //For shopping cart
        val menuItem = menu.findItem(R.id.action_shop)
        val actionView = MenuItemCompat.getActionView(menuItem)
        actionView.setOnClickListener { onOptionsItemSelected(menuItem) }
        textCartItemCount = actionView.findViewById(R.id.cart_badge) as TextView
        textCartItemCount!!.text = "" + shoppingNumbers
        
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(getString(R.string.bought_items), shoppingNumbers!!)
        Log.i("shopping number", shoppingNumbers.toString())
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.action_home -> {
                Toast.makeText(this, "Already at home", Toast.LENGTH_SHORT).show()
            }
            R.id.action_shop -> {
                val intent = Intent(this, ShopActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun OnBookReceived(books: List<Book>){
        val listView = findViewById<ListView>(R.id.bookListView)
        listView.adapter = BookAdapter(this, books)

        //get the number of books bought by user in userPrefs
        var buyCounter =0
        for (book in books){
            buyCounter += sharedPref.getInt(book.isbn, 0)
        }
        shoppingNumbers = buyCounter
        textCartItemCount!!.text = "" + shoppingNumbers

    }

    override fun userWantToBuyBook(book: Book) {
        Toast.makeText(this, "Shop Not Available for " + book.title, Toast.LENGTH_LONG).show()

        shoppingNumbers = shoppingNumbers?.plus(1)
        //could be handle by databinding
        textCartItemCount!!.text = "" + shoppingNumbers

        //put a new book in the sharedPrefs file
        val bookItems = sharedPref.getInt(book.isbn, 0)
        val editor = sharedPref.edit()
        editor.putInt(book.isbn, bookItems+1)
        editor.apply()
    }

    override fun userWantToGetInfoOnBook(book: Book) {
        Toast.makeText(this, "Get description for " + book.title, Toast.LENGTH_LONG).show()
        val intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra(getString(R.string.detail_intent_key), book)
        intent.putExtra(getString(R.string.detail_intent_items), shoppingNumbers)
        startActivity(intent)
    }
}

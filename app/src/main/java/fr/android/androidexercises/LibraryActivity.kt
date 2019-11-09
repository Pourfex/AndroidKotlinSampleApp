package fr.android.androidexercises

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import java.util.ArrayList
import java.util.Locale
import java.util.Random

class LibraryActivity : AppCompatActivity() {

    companion object {
        private val RANDOM = Random()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        val books = books

        // TODO findViewById() and setAdapter()
    }

    private val books: List<Book>
        get() {
            val books = ArrayList<Book>()
            for (i in 0..99) {
                books.add(
                        Book(String.format(Locale.FRANCE, "Garry Potier Tome %d", i),
                                RANDOM.nextInt(30).toFloat())
                )
            }
            return books
        }

}

package fr.android.androidexercises.MainBookActivity.Service

import fr.android.androidexercises.Global.Book
import retrofit2.http.GET


interface HenriPotierService {
    // TODO Method GET books which return a List<Book>

    @GET("books")
    suspend fun getBooks(): List<Book>
}

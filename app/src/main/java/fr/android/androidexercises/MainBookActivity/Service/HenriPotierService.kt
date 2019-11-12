package fr.android.androidexercises.MainBookActivity.Service

import fr.android.androidexercises.Global.Book
import retrofit2.http.GET

//interface to call henri potier jsonserver as a service
interface HenriPotierService {
    @GET("books")
    suspend fun getBooks(): List<Book>
}

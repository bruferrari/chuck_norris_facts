import com.bferrari.domain.Category
import com.bferrari.domain.PastSearch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class TestUtils {

    companion object {
        const val HTTP_OK = 200

        val pastSearch = PastSearch("test")
        val pastSearches = listOf(pastSearch)
        val exception = Exception()

        val category = Category(1, "Test")
        val categories = listOf(category)

        val categoryResponse = listOf("Test")

        fun retrofitInstance(baseUrl: String): Retrofit {
            val okHttpClient = OkHttpClient.Builder().build()

            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
    }
}
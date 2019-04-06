import com.bferrari.domain.PastSearch
import java.lang.Exception

class TestUtils {

    companion object {
        const val HTTP_OK = 200

        val pastSearch = PastSearch("test")
        val pastSearches = listOf(pastSearch)
        val exception = Exception()
    }
}
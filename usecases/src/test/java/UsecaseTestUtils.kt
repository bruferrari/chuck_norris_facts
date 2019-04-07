import com.bferrari.domain.Category
import com.bferrari.domain.Fact
import com.bferrari.domain.PastSearch
import java.lang.Exception

class UsecaseTestUtils {
    companion object {
        val exception = Exception()

        val category = Category(1, "Test")
        val categories = listOf(category)

        val query = "test"
        val facts = listOf(Fact(id = "", value = "test fact"))

        val search = PastSearch("test")
        val searches = listOf(search)
    }
}
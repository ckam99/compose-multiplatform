import androidx.compose.ui.window.ComposeUIViewController
import org.example.grocery.App
import org.example.grocery.core.database.DatabaseDriverFactory

fun MainViewController() = ComposeUIViewController {
    val sqlDriver =  DatabaseDriverFactory()
    App(sqlDriver)
}
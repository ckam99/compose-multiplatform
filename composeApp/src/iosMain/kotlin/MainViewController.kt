import androidx.compose.ui.window.ComposeUIViewController
import org.example.grocery.App
import org.example.grocery.core.di.KoinInitializer

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    App()
}
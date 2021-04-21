package dev.m13d.networkstatustracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: NetworkStatusViewModel by lazy {
        ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    val networkStatusTracker = NetworkStatusTracker(this@MainActivity)
                    return NetworkStatusViewModel(networkStatusTracker) as T
                }
            },
        ).get(NetworkStatusViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.state.observe(this) { state ->
            findViewById<TextView>(R.id.textView).text = when (state) {
                MyState.Fetched -> "Online"
                MyState.Error -> "Offline"
                else -> "???"
            }
        }
    }
}
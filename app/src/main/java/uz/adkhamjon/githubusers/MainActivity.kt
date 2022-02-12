package uz.adkhamjon.githubusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import uz.adkhamjon.githubusers.common.RvAdapter
import uz.adkhamjon.githubusers.databinding.ActivityMainBinding
import uz.adkhamjon.githubusers.domain.use_cases.get_users.UserResource
import uz.adkhamjon.githubusers.viewmodels.UsersViewModel
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {
    private val usersViewModel: UsersViewModel by viewModels()
    private lateinit var rvAdapter: RvAdapter
    private lateinit var binding: ActivityMainBinding
    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        @OptIn(InternalCoroutinesApi::class)
        CoroutineScope(Dispatchers.Main).launch {
            usersViewModel.getStateFlow().collect {
                when (it) {
                    is UserResource.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                    }
                    is UserResource.Error -> {
                        binding.progress.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                    is UserResource.Success -> {
                        rvAdapter = RvAdapter(this@MainActivity)
                        binding.progress.visibility = View.GONE
                        rvAdapter.submitList(it.list)
                        binding.recView.adapter = rvAdapter
                    }
                }
            }
        }
    }
    override val coroutineContext: CoroutineContext
        get() = Job()
}
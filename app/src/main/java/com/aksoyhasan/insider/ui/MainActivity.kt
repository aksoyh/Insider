package com.aksoyhasan.insider.ui

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.aksoyhasan.insider.BuildConfig
import com.aksoyhasan.insider.R
import com.aksoyhasan.insider.base.BaseActivity
import com.aksoyhasan.insider.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var content: View

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    lateinit var navController: NavController

    override fun observeViewModel() {}

    override fun initViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.acMainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Log.d("MainActivity", "onCreate: I AM RUNNING ON API 12 or higher")
            // TODO: show main fragment
            content.viewTreeObserver.addOnPreDrawListener(object :
                ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean =
                    when {
                        viewModel.mockDataLoading() -> {
                            content.viewTreeObserver.removeOnPreDrawListener(this)
                            true
                        }
                        else -> false
                    }
            })


            splashScreen.setOnExitAnimationListener { splashScreenView ->
                ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_X,
                    0f,
                    splashScreenView.width.toFloat()
                ).apply {
                    duration = 1000
                    doOnEnd {
                        splashScreenView.remove()
                    }
                }.also {
                    it.start()
                }
            }
        } else {
            // TODO: show splash fragment

        }
    }
}
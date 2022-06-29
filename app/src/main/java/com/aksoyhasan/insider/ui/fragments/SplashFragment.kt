package com.aksoyhasan.insider.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import com.aksoyhasan.insider.R
import com.aksoyhasan.insider.base.BaseFragment
import com.aksoyhasan.insider.databinding.FragmentSplashBinding
import com.aksoyhasan.insider.ui.MainActivity
import com.aksoyhasan.insider.ui.MainViewModel
import com.aksoyhasan.insider.util.InternetConnectionHelper
import com.aksoyhasan.insider.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    private val args: SplashFragmentArgs by navArgs()

    private var deleyTime: Long = 0

    override fun observeViewModel() {}

    override fun initViewBinding() {
        binding = FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity = requireActivity() as MainActivity
        navController = mainActivity.navController

        deleyTime = if (args.isBelowToS) {
            0
        } else {
            2000
        }
        val handler = Handler()

        if (InternetConnectionHelper.isInternetOn()) {handler.postDelayed({
            navController.navigate(R.id.action_splashFragment_to_mainFragment)
        }, deleyTime)
        } else {
            binding.root.showSnackbar(resources.getString(R.string.network_error), 1500)
            handler.postDelayed({
                mainActivity.finish()
            }, 2000)
        }
    }

}
package com.aksoyhasan.insider.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.aksoyhasan.insider.BuildConfig
import com.aksoyhasan.insider.R
import com.aksoyhasan.insider.base.BaseFragment
import com.aksoyhasan.insider.data.Resource
import com.aksoyhasan.insider.data.dto.StarDTO
import com.aksoyhasan.insider.databinding.FragmentMainBinding
import com.aksoyhasan.insider.error.SHOW_SERVER_MESSAGE
import com.aksoyhasan.insider.ui.MainActivity
import com.aksoyhasan.insider.ui.MainViewModel
import com.aksoyhasan.insider.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    private val smallStarList: MutableList<Any> = mutableListOf()
    private val brightStarList: MutableList<Any> = mutableListOf()

    override fun observeViewModel() {
        observe(viewModel.starLiveData, ::handleWebView)
    }

    private fun handleWebView(status: Resource<StarDTO>) {
        when (status) {
            is Resource.Loading -> {
                mainActivity.showLoading()
            }
            is Resource.DataError -> {
                mainActivity.hideLoading()
                status.errorCode?.let {
                    if (it == SHOW_SERVER_MESSAGE) {
                        binding.root.showSnackbar(status.errorMessage.emptyIfNull(), 1500)
                    } else {
                        val error = viewModel.errorManager.getError(it)
                        binding.root.showSnackbar(error.description, 1500)
                    }
                }
            }
            is Resource.Success -> {
                mainActivity.hideLoading()
                status.data.let {
                    Log.d("LOG_X_STAR_OBJ", it.toString())
                    if (it?.brightness.emptyIfNull() == "BRIGHT") {
                        brightStarList.add(smallStarList.size)
                        Log.d("LOG_X_NUM_OF_BRIGHT_STAR", brightStarList.size.toString())
                    }
                    with(binding) {
                        val url = BuildConfig.URL_FULL_ADDRESS.replaceRange(48, 51, it?.size.toString())
                        webView.loadUrl(url)
                    }
                }
            }
        }
    }

    override fun initViewBinding() {
        binding = FragmentMainBinding.inflate(layoutInflater)
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


        with(binding) {
            webView.loadUrl(BuildConfig.URL_FULL_ADDRESS)
            btnInfo.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_aboutFragment) }

            btnSmall.setOnClickListener {
                handleButtonWorks(true)
                smallStarList.add(smallStarList.size)
                if (smallStarList.size > 10) {
                    binding.root.showSnackbar("Sky is full", 2000)
                    Log.d("LOG_X_ONCLICK_SMALL BUTTON", smallStarList.toString())
                }
            }
            btnBig.setOnClickListener { handleButtonWorks(false) }
        }

    }

    private fun handleButtonWorks(isSmall: Boolean) {
        try {
            viewModel.getStarObj(isSmall)
        } catch (e: Exception) {
            binding.root.showSnackbar(requireContext().resources.getString(R.string.common_error), 1500)
        }
    }
}
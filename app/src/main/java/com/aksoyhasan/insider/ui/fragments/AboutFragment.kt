package com.aksoyhasan.insider.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.aksoyhasan.insider.base.BaseFragment
import com.aksoyhasan.insider.databinding.FragmentAboutBinding
import com.aksoyhasan.insider.ui.MainActivity
import com.aksoyhasan.insider.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutFragment : BaseFragment() {

    private lateinit var binding: FragmentAboutBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var navController: NavController

    override fun observeViewModel() {}

    override fun initViewBinding() {
        binding = FragmentAboutBinding.inflate(layoutInflater)
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

        binding.btnBack.setOnClickListener { mainActivity.onBackPressed() }

    }
}
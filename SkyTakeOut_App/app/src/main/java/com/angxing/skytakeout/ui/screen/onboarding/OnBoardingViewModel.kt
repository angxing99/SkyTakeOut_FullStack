package com.angxing.skytakeout.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import com.angxing.skytakeout.R

class OnBoardingViewModel : ViewModel() {

    private val _pages = listOf(
        OnBoardingPage(R.drawable.page_1, "Find your Comfort Food Here", "Here you can find a chef or dish for every taste and color. Enjoy!."),
        OnBoardingPage(R.drawable.page_2, "Sky Take Out is Where Your Comfort Food Lives", "Enjoy a fast and smooth food delivery at your doorstep")
    )

    val pages: List<OnBoardingPage> = _pages



}
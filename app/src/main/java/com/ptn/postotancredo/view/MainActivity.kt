package com.ptn.postotancredo.view

import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Fade
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.sidesheet.SideSheetBehavior
import com.ptn.postotancredo.R
import com.ptn.postotancredo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sideSheetBehavior: SideSheetBehavior<FrameLayout>
    private val fragmentMain = MainFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sideSheetBehavior = SideSheetBehavior.from(binding.sideSheet)
        sideSheetBehavior.state = SideSheetBehavior.STATE_HIDDEN

        bottomBarNavigation(fragmentMain)
        onClickProfile()

    }

    private fun onClickProfile() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> {
                    configBottomSeet()
                }

                R.id.history -> {
                    val history = HistoryFragment()
                    bottomBarNavigation(history)
                    checkMenu()
                }

                R.id.scheduled -> {
                    val scheduled = ScheduledFragment()
                    bottomBarNavigation(scheduled)
                    checkMenu()
                }

                R.id.home -> {
                    bottomBarNavigation(fragmentMain)
                    checkMenu()
                }


            }
            true
        }
    }

    private fun configBottomSeet() {
        if (sideSheetBehavior.state == SideSheetBehavior.STATE_HIDDEN) {
            sideSheetBehavior.state = SideSheetBehavior.STATE_EXPANDED

        } else {
            sideSheetBehavior.state = SideSheetBehavior.STATE_HIDDEN
        }
    }

    private fun checkMenu() {
        if (sideSheetBehavior.state == SideSheetBehavior.STATE_EXPANDED) {
           sideSheetBehavior.state = SideSheetBehavior.STATE_HIDDEN
        }
    }

    private fun bottomBarNavigation(fragment: Fragment){
        val navigation = supportFragmentManager.beginTransaction()
        navigation.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        navigation.replace(R.id.container_main, fragment)
        navigation.commit()

    }
}


package com.ptn.postotancredo.view

import android.content.SharedPreferences
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
import com.google.firebase.auth.FirebaseAuth
import com.ptn.postotancredo.R
import com.ptn.postotancredo.dataBase.FirebaseHelper
import com.ptn.postotancredo.databinding.ActivityMainBinding
import com.ptn.postotancredo.service.auth.GlobalTokenValue

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    private lateinit var sideSheetBehavior: SideSheetBehavior<FrameLayout>
    private val fragmentMain = MainFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSharedPreference()
        sideSheetBehavior = SideSheetBehavior.from(binding.sideSheet)
        sideSheetBehavior.state = SideSheetBehavior.STATE_HIDDEN

        bottomBarNavigation(fragmentMain)
        onClickProfile()
        rightSheetBarClick()

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
    private fun logOut(){
        val auth: FirebaseAuth = FirebaseAuth.getInstance()
        auth.signOut()
        finish()
    }
    private fun rightSheetBarClick(){
        binding.logout.setOnClickListener {
            logOut()
        }
    }

    private fun initSharedPreference(){
        sharedPreferences =getSharedPreferences("token_jwt", MODE_PRIVATE)
        GlobalTokenValue.tokenValue = sharedPreferences.getString("token_jwt", null).toString()
    }
}



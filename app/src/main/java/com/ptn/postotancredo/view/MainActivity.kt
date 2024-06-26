package com.ptn.postotancredo.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.sidesheet.SideSheetBehavior
import com.google.gson.Gson
import com.ptn.postotancredo.R
import com.ptn.postotancredo.databinding.ActivityMainBinding
import com.ptn.postotancredo.service.Dto.UserDataResponse
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

        binding.sideSheetUsername.text = GlobalTokenValue.userDataResponse?.user?.firstName

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)
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
                    val history = HistoricFragment()
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

    private fun bottomBarNavigation(fragment: Fragment) {
        val navigation = supportFragmentManager.beginTransaction()
        navigation.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        navigation.replace(R.id.container_main, fragment)
        navigation.commit()
    }


    private fun rightSheetBarClick() {
        binding.logout.setOnClickListener {

        }
    }

    private fun initSharedPreference() {
        val gson = Gson()
        val userInfo = sharedPreferences.getString("userData", null)
        if (userInfo != null) {
            val userData = userInfo.let { gson.fromJson(it, UserDataResponse::class.java) }
            GlobalTokenValue.initUserData(userData)
        } else Log.d("MainActivity", "O user data está vazio")


    }

    private fun isAthenticated(){
        if(GlobalTokenValue.userDataResponse == null){
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}



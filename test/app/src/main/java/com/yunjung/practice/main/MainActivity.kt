package com.yunjung.practice.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yunjung.practice.R
import com.yunjung.practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainViewModel

    // Navigation Component 관련 변수
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var host : NavHostFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        /* Navigation Component 관련 */
        // 툴바 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 안드로이드에서 자동으로 넣는 툴바의 타이틀 제거

        // NavHost를 이용하여 NavController가져오기
        host = supportFragmentManager.findFragmentById(R.id.fragment_frame) as NavHostFragment? ?: return
        navController = host.navController

        // 최상위 수준의 화면 지정
        appBarConfiguration = AppBarConfiguration(setOf(R.id.oneFragment, R.id.twoFragment, R.id.threeFragment))

        // 액션바에 navController와 appBarConfiguration객체를 설정
        setupActionBar(navController, appBarConfiguration)

        setTitleAndBackButton()

        initBottomNavigation()
    }

    // 툴바를 액션바로 지정
    private fun setupActionBar(navController: NavController,
                               appBarConfig : AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    // back button이 동작하도록 함
    override fun onSupportNavigateUp(): Boolean {
        return host.navController.navigateUp() || super.onSupportNavigateUp()
    }

    // 전환되는 Fragment에 따라 적절한 Title과 BackButton이 보이도록 함
    private fun setTitleAndBackButton(){
        val toolbar = binding.toolbar
        val title = binding.titleView

        // 탐색이 수행 될 마다 실행됨
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // back button icon 이미지 설정 or Toolbar title 설정 etc..
            // ex)
            when (destination.id) {
                R.id.oneFragment -> {
                    title.text = "oneFragment"
                }
                R.id.twoFragment -> {
                    title.text = "twoFragment"
                }
                R.id.threeFragment ->{
                    title.text = "threeFragment"
                }
                R.id.fourFragment -> {
                    toolbar.setNavigationIcon(R.drawable.ic_bacck)
                    title.text = "fourFragment"
                }
            }
        }
    }

    // Bottom Navation이 실질적으로 동작하게 함
    private fun initBottomNavigation(){
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item->
            when(item.itemId){
                R.id.shopping -> {
                    navController.navigate(R.id.action_global_oneFragment)
                }
                R.id.home->{
                    navController.navigate(R.id.action_global_twoFragment)
                }
                R.id.news->{
                    navController.navigate(R.id.action_global_threeFragment)
                }
            }
            true
        }
    }
}
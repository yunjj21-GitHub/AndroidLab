package com.yunjung.test.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.yunjung.test.R
import com.yunjung.test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainViewModel

    // Navigation Component 관련 변수
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var host : NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        // 필요한 뷰를 가져옴
        val toolbar = binding.toolbar
        val titleView = binding.titleView

        /* Navigation Component 관련 */
        // 툴바 설정
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 안드로이드에서 자동으로 넣는 툴바의 타이틀 제거

        // NavHost를 이용하여 NavController가져오기
        host = supportFragmentManager.findFragmentById(R.id.fragment_frame) as NavHostFragment? ?: return
        val navController = host.navController

        // 최상위 수준의 화면 지정
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // 액션바에 navController와 appBarConfiguration객체를 설정
        setupActionBar(navController, appBarConfiguration)

        // 탐색이 수행 될 마다 실행됨
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // back button icon 이미지 설정 or Toolbar title 설정 etc..
            // ex)
            when (destination.id) {
                R.id.oneFragment -> {
                    titleView.text = "oneFragment"
                }
                R.id.twoFragment -> {
                    toolbar.setNavigationIcon(R.drawable.ic_bacck)
                    titleView.text = "twoFragment"
                }
            }
        }
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
}
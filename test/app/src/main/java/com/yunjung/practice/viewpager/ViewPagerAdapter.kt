package com.yunjung.practice.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yunjung.practice.economic.EconomicFragment
import com.yunjung.practice.entertainment.EntertainmentFragment
import com.yunjung.practice.news.NewsFragment
import com.yunjung.practice.sport.SportsFragment

private const val NUM_PAGES = 4

class ViewPagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
    // 어댑터에서 만들 페이지의 수를 반환
    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    // position에 프래그먼트의 인스턴스를 새 페이지로 반환
    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> NewsFragment()
            1-> EntertainmentFragment()
            2-> SportsFragment()
            else -> EconomicFragment()
        }
    }
}
package com.yunjung.practice.four

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yunjung.practice.R
import com.yunjung.practice.databinding.FragmentFourBinding
import com.yunjung.practice.viewpager.ViewPagerAdapter

class FourFragment : Fragment() {
    lateinit var binding: FragmentFourBinding
    lateinit var viewModel: FourViewModel

    lateinit var viewPager: ViewPager2

    // 뷰가 생성될 때 실행
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_four, container, false)
        return binding.root
    }

    // 뷰가 완전히 생성되었을 때 실행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FourViewModel::class.java)
        binding.viewModel = viewModel

        viewPager = binding.viewPager

        initViewPager()
        initTabLayoutWithViewPager()
    }

    // 뷰페이저에 어댑터 부착
    private fun initViewPager() {
        val pagerAdapter = ViewPagerAdapter(requireActivity())
        viewPager.adapter = pagerAdapter
    }

    // 탭 레이아웃과 뷰페이저를 함께 사용할 수 있도록 셋팅
    private fun initTabLayoutWithViewPager() {
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "뉴스"
                1 -> tab.text = "연예"
                2 -> tab.text = "스포츠"
                3 -> tab.text = "경제지표"
            }
        }.attach()
    }
}
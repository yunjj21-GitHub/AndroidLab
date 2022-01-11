package com.yunjung.practice.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.yunjung.practice.R
import com.yunjung.practice.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    lateinit var binding : FragmentNewsBinding
    lateinit var viewModel : NewsViewModel

    // 뷰가 생성될 때 실행
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    // 뷰가 완전히 생성되었을 때 실행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        binding.viewModel = viewModel

        // imageView 이미지 처리
        imageLoadAndDisplay(binding.imageView, "https://w.namu.la/s/43d991eb90905f3532cac04e8764f211911dccc3f80f952dd2f2e57579b15a48f2e89403659b830a0fbf1b0ef21898da36da076dd20ab6132e46c4ec7d5fd5a2da55eb6c0835a4113fea76e8b354bc660540218b8160da4e9d314538163f6f0e93d58b19a43588a591f738be9b0fe2d9")
    }

    // 서버에서 이미지를 로드하여 뷰에 표시하는 메서드
    private fun imageLoadAndDisplay(imageView : ImageView, imageUrl : String){
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.loading)
            .error(R.drawable.brocked_image)
            .into(imageView)
    }
}
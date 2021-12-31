package com.yunjung.practice.two

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yunjung.practice.R
import com.yunjung.practice.databinding.FragmentTwoBinding

class TwoFragment : Fragment() {
    lateinit var binding : FragmentTwoBinding
    lateinit var viewModel : TwoViewModel

    // 뷰가 생성될 때 실행
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_two, container, false)
        return binding.root
    }

    // 뷰가 완전히 생성되었을 때 실행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(TwoViewModel::class.java)
        binding.viewModel = viewModel

        binding.shareBtn.setOnClickListener {
            showPopUpForSharing("공유할 내용")
        }
    }

    // 공유하기 팝업창을 띄움
    fun showPopUpForSharing(content : String){
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, content)
        }
        startActivity(Intent.createChooser(shareIntent, null))
    }
}
package com.yunjung.practice.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.yunjung.practice.R
import com.yunjung.practice.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    lateinit var binding : FragmentOneBinding
    lateinit var viewModel : OneViewModel

    // 뷰가 생성될 때 실행
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false)
        return binding.root
    }

    // 뷰가 완전히 생성되었을 때 실행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(OneViewModel::class.java)
        binding.viewModel = viewModel

        val moveBtn = binding.button
        moveBtn.setOnClickListener {
            findNavController().navigate(R.id.action_oneFragment_to_twoFragment)
        }
    }
}
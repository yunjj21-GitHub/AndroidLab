package com.yunjung.practice.result

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.yunjung.practice.R
import com.yunjung.practice.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    lateinit var binding : FragmentResultBinding
    lateinit var viewModel : ResultViewModel

    private val args : ResultFragmentArgs by navArgs()

    // 뷰가 생성될 때 실행
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)
        return binding.root
    }

    // 뷰가 완전히 생성되었을 때 실행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)
        binding.viewModel = viewModel

        val userInput = args.userInput
        viewModel.updateUserValue(userInput)
    }
}
package com.yunjung.practice.three

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yunjung.practice.R
import com.yunjung.practice.databinding.FragmentThreeBinding
import com.yunjung.practice.recycler.UserRecyclerAdapter

class ThreeFragment : Fragment() {
    lateinit var binding : FragmentThreeBinding
    lateinit var viewModel : ThreeViewModel

    // 뷰가 생성될 때 실행
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_three, container, false)
        return binding.root
    }

    // 뷰가 완전히 생성되었을 때 실행
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ThreeViewModel::class.java)
        binding.viewModel = viewModel

        initRecycler()
        subscribeUserList()
    }

    // 리사이클러뷰에 어댑터를 부착
    fun initRecycler(){
        binding.recyclerView.adapter =UserRecyclerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    // userList가 변경되면 실행
    fun subscribeUserList(){
        viewModel.userList.observe(viewLifecycleOwner, {
            (binding.recyclerView.adapter as UserRecyclerAdapter).submitList(it)
        })
    }
}
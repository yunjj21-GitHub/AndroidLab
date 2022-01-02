package com.yunjung.practice.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yunjung.practice.databinding.ItemUserBinding
import com.yunjung.practice.models.User

// 어댑터
class UserRecyclerAdapter : ListAdapter<User, UserViewHolder>(UserDataDiff){

    lateinit var binding : ItemUserBinding
    lateinit var layoutInflater : LayoutInflater

    // 새로운 뷰가 생성될 때
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemUserBinding.inflate(layoutInflater)
        return UserViewHolder(binding)
    }

    // 뷰의 내용물이 대체될 때
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.initBinding(getItem(position))

        // 아이템이 클릭 되었을 때
        holder.itemView.setOnClickListener {

        }
    }
}

// 뷰홀더
class UserViewHolder(
    private val binding : ItemUserBinding
): RecyclerView.ViewHolder(binding.root) {
    fun initBinding(data: User) {
        binding.item = data
    }
}

// 데이터가 달리지면 갱신히도록 함
object UserDataDiff : DiffUtil.ItemCallback<User>() {
    // 데이터의 고유의 아이디만 비교
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem._id == newItem._id
    }

    // 데이터 전체를 비교
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
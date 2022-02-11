package uz.adkhamjon.githubusers.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.adkhamjon.githubusers.databinding.LinearItemBinding
import uz.adkhamjon.githubusers.domain.model.GitUserModel

class RvAdapter (
    var context: Context
    ) :
    ListAdapter<GitUserModel, RvAdapter.Linear>(MyDiffUtil()) {
    class MyDiffUtil : DiffUtil.ItemCallback<GitUserModel>() {
        override fun areItemsTheSame(oldItem: GitUserModel, newItem: GitUserModel): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: GitUserModel, newItem: GitUserModel): Boolean {
            return oldItem == newItem
        }
    }
    inner class Linear(var linearItemBinding: LinearItemBinding)
        : RecyclerView.ViewHolder(linearItemBinding.root){
        fun onBind(gitUserModel: GitUserModel) {
            linearItemBinding.name.text=gitUserModel.login
            Glide.with(context).load(gitUserModel.avatar_url)
                .into(linearItemBinding.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Linear {
        return Linear(
            LinearItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Linear, position: Int) {
        getItem(position)?.let { users ->
            holder.onBind(users)
            holder.itemView.setOnClickListener {

            }
        }
    }
}
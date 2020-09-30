package ru.samtakot.rttest.presentation.list.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_user.view.*
import ru.samtakot.rttest.R
import ru.samtakot.rttest.domain.entity.User


class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind(item: User, itemClickListener: ((view: View, empl: User) -> Unit)) {

        itemView.setOnClickListener(View.OnClickListener {
            itemClickListener.invoke(it, item)
        })

        itemView.firstName.text = item.firstName
        itemView.lastName.text = item.lastName
        Glide.with(itemView.context)
            .load(item.avatar)
            .fitCenter()
            .placeholder(R.drawable.ic_person_gray_24dp)
            .error(R.drawable.ic_err_person_gray_24dp)
            .into(itemView.avatar)
    }


}

class UsersListAdapter (
    private val itemClickListener:((view: View, user: User) -> Unit)
) : ListAdapter<User, UserViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(
            inflater.inflate(R.layout.list_item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item: User = getItem(position)
        holder.bind(item, itemClickListener)
    }

}


private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
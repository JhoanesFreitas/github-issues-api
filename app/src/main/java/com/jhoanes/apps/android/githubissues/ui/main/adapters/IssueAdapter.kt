package com.jhoanes.apps.android.githubissues.ui.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jhoanes.apps.android.githubissues.R
import com.jhoanes.apps.android.githubissues.applications.GIApplication.Companion.context
import com.jhoanes.apps.android.githubissues.models.IssueModel
import com.jhoanes.apps.android.githubissues.services.ViewCallback
import com.jhoanes.apps.android.githubissues.ui.details.DetailActivity
import de.hdodenhof.circleimageview.CircleImageView

class IssueAdapter(private val callback: ViewCallback<IssueModel>) :
    RecyclerView.Adapter<IssueAdapter.IssueHolder>() {

    var issues = mutableListOf<IssueModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueHolder {
        return IssueHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_issues_item, parent, false)
        )
    }

    override fun getItemCount(): Int =
        issues.size

    override fun onBindViewHolder(holder: IssueHolder, position: Int) {
        holder.mTitleTV?.text = issues[position].title
        holder.mStatusTV?.text = issues[position].state

        Glide.with(context)
            .load(issues[position].user?.avatar)
            .into(holder.profileImage)

        holder.cardView?.setOnClickListener {
            callback.showProgressCentral()
            callback.startActivity(issues[position], DetailActivity::class)
        }
    }

    fun replaceAll(issues: MutableList<IssueModel>) {
        this.issues = issues
        notifyDataSetChanged()
    }

    class IssueHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage = itemView.findViewById<CircleImageView?>(R.id.profile_image)
        val cardView = itemView.findViewById<CardView?>(R.id.cardView)
        val mTitleTV = itemView.findViewById<TextView?>(R.id.title)
        val mStatusTV = itemView.findViewById<TextView?>(R.id.status)
    }
}
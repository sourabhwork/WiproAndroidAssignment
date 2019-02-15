package com.infosys.telstra.android.telstraandroidassignment.view

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.wipro.android.wiproandroidassignment.R
import com.wipro.android.wiproandroidassignment.repository.db.Fact
import kotlinx.android.synthetic.main.fragment_facts.view.*

/**
 * Adapter class for fact list
 */
class MyFactsRecyclerViewAdapter(
    context: FragmentActivity?
) : RecyclerView.Adapter<MyFactsRecyclerViewAdapter.ViewHolder>() {

    var mFactList: List<Fact> = ArrayList<Fact>()
    var mContext:Context = context as Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(mContext).inflate(R.layout.fragment_facts, parent, false);
        return ViewHolder(view)
    }


    override fun getItemCount(): Int = mFactList.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {

        var txtFactTitle = mView.txtFactTitle
        var txtFactDescriptin = mView.txtFactDescription
        var imageView = mView.imgFact
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = mFactList.get(position)
        Glide.with(mContext)
            .load(fact.imageHref)
            .apply( RequestOptions()
            .placeholder(R.drawable.ic_not_found)
                .error(R.drawable.ic_not_found))
            .into(holder.imageView.imgFact);
        holder.txtFactTitle.setText(fact.title)
        holder.txtFactDescriptin.setText(fact.description);

    }

    public fun swapList(newFactList: List<Fact>?) {
        if (newFactList != null) {
            mFactList = newFactList
            notifyDataSetChanged()
        }
    }
}

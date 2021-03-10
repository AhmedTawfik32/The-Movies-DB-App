package com.ahmedtawfik.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedtawfik.moviesapp.R
import com.ahmedtawfik.moviesapp.model.entity.Cast
import com.ahmedtawfik.moviesapp.utils.GetImageURL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_cast.view.*
import kotlin.properties.Delegates

class CastAdapter : RecyclerView.Adapter<CastViewHolder>() {

    var casts: List<Cast> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    fun updateCast(casts: List<Cast>) {
        this.casts = casts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return casts.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(casts[position])
    }

}

class CastViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    fun bind(cast: Cast) {
        var requestOption = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
        itemView.run {
            if (cast != null) {
                tv_actorName_cast.text = cast.original_name
                tv_actorNameInMovie_cast.text = cast.character
                Glide.with(this)
                    .load(GetImageURL.getImageFullURL(cast.profile_path.toString()))
                    .fitCenter()
                    .apply(requestOption)
                    .placeholder(R.drawable.ic_baseline_image)
                    .into(iv_actorPhoto_cast)
            }
        }
    }

}

package com.ahmedtawfik.moviesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedtawfik.moviesapp.R
import com.ahmedtawfik.moviesapp.model.entity.ProductionCompany
import com.ahmedtawfik.moviesapp.utils.GetImageURL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_company_production.view.*
import kotlin.properties.Delegates

class ProductionCompanyAdapter : RecyclerView.Adapter<ProductionCompanyViewHolder>() {

    var productionCompanies: List<ProductionCompany> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    fun updateProductionCompany(productionCompanies: List<ProductionCompany>) {
        this.productionCompanies = productionCompanies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionCompanyViewHolder {
        var view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_company_production, parent, false)
        return ProductionCompanyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productionCompanies.size
    }

    override fun onBindViewHolder(holder: ProductionCompanyViewHolder, position: Int) {
        holder.bind(productionCompanies[position])
    }

}

class ProductionCompanyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    fun bind(productionCompany: ProductionCompany) {
        var requestOption = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
        itemView.run {
            if (productionCompany != null) {
                tv_productioCompany.text = productionCompany.name

                Glide.with(this)
                    .load(GetImageURL.getImageFullURL(productionCompany.logo_path.toString()))
                    .fitCenter()
                    .apply(requestOption)
                    .placeholder(R.drawable.ic_baseline_image)
                    .into(iv_logo_CompanyProduction)
            }
        }
    }

}

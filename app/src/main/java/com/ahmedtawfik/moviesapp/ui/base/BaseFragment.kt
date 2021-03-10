package com.ahmedtawfik.moviesapp.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ahmedtawfik.moviesapp.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<out ViewModelType : BaseViewModel>(kClass: KClass<ViewModelType>) :
    Fragment() {

    open val viewModel: ViewModelType by viewModel(kClass)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initObservers()
    }

    open fun initObservers() {
        viewModel.networkStatus.observe(viewLifecycleOwner, Observer {
            var state = it ?: return@Observer
            when (state.status) {
                Status.RUNNING -> showLoading()
                Status.SUCCESS -> hideLoading()
                Status.FAILED -> {
                    hideLoading()
                    Toast.makeText(requireContext(), "Failed to get data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    abstract fun hideLoading()

    abstract fun showLoading()

    abstract fun initViews()

}
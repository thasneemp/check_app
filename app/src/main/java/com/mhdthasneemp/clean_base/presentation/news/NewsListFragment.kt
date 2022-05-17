package com.mhdthasneemp.clean_base.presentation.news

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.mhdthasneemp.clean_base.BR
import com.mhdthasneemp.clean_base.R
import com.mhdthasneemp.clean_base.databinding.NewsListFragmentBinding
import com.mhdthasneemp.clean_base.domain.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment : BaseFragment<NewsListFragmentBinding>() {

    private val viewModel: NewsListFragmentViewModel by viewModels()


    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.news_list_fragment
    }

    override fun getViewModel(): ViewModel {
        return viewModel
    }

    override fun observeOnStarted() {
        viewModel.mState.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state ->
                when (state) {
                    is NewsListFragmentState.Init -> {
                        Unit
                    }
                    is NewsListFragmentState.IsLoading -> {
                        showLoading(show = state.isLoading)
                    }
                    is NewsListFragmentState.ShowToast -> {

                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


}
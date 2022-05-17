package com.mhdthasneemp.clean_base.presentation.news

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.viewModelScope
import com.mhdthasneemp.clean_base.BR
import com.mhdthasneemp.clean_base.R
import com.mhdthasneemp.clean_base.domain.base.BaseFragmentViewModel
import com.mhdthasneemp.clean_base.domain.base.BaseResult
import com.mhdthasneemp.clean_base.domain.news.entity.ResultEntity
import com.mhdthasneemp.clean_base.domain.news.usecase.NewsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import javax.inject.Inject

@HiltViewModel
class NewsListFragmentViewModel @Inject constructor(private val useCase: NewsListUseCase) :
    BaseFragmentViewModel() {
    private val state = MutableStateFlow<NewsListFragmentState>(NewsListFragmentState.Init)
    val mState: StateFlow<NewsListFragmentState> get() = state

    val items = ObservableArrayList<ResultEntity>()
    val itemBind = ItemBinding.of<ResultEntity>(BR.items, R.layout.news_list_items)

    init {
        getAllNews()
    }

    private fun getAllNews() {
        viewModelScope.launch {
            useCase.execute().onStart {
                state.value = NewsListFragmentState.IsLoading(true)
            }.catch {
                state.value = NewsListFragmentState.IsLoading(false)
            }.collect { result ->
                state.value = NewsListFragmentState.IsLoading(false)
                when (result) {
                    is BaseResult.Success -> {
                        items.clear()
                        items.addAll(result.data.results ?: emptyList())
                    }
                    else -> {

                    }
                }
            }
        }
    }
}

sealed class NewsListFragmentState {
    object Init : NewsListFragmentState()
    data class IsLoading(val isLoading: Boolean) : NewsListFragmentState()
    data class ShowToast(val message: String) : NewsListFragmentState()
}
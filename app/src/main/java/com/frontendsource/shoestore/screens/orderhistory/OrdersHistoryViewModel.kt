package com.frontendsource.shoestore.screens.orderhistory


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frontendsource.shoestore.models.OrderDetails
import com.frontendsource.shoestore.repositories.ProductsRepository
import com.frontendsource.shoestore.sealed.DataResponse
import com.frontendsource.shoestore.sealed.Error
import com.frontendsource.shoestore.sealed.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class OrdersHistoryViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
) : ViewModel() {
    private val _orders: MutableList<OrderDetails> = mutableStateListOf()
    val orders: List<OrderDetails> = _orders

    private val _historyUiState = mutableStateOf<UiState>(UiState.Loading)
    val historyUiState: State<UiState> = _historyUiState

    fun getOrders() {
        _historyUiState.value = UiState.Loading
        viewModelScope.launch {
            productsRepository.getOrdersHistory().let {
                when (it) {
                    is DataResponse.Success -> {
                        /** Got a response from the server successfully */
                        _historyUiState.value = UiState.Success
                        it.data?.let { data ->
                            _orders.addAll(data)
                        }
                    }
                    is DataResponse.Error -> {
                        /** An error happened when fetching data from the server */
                        _historyUiState.value = UiState.Error(error = it.error ?: Error.Unknown)
                    }
                }
            }
        }
    }
}
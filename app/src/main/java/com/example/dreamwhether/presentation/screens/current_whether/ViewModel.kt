package com.example.dreamwhether.presentation.screens.current_whether

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dreamwhether.domain.use_cases.GetWhetherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dreamwhether.common.RequestStates
import com.example.dreamwhether.data.db.CityEntity
import com.example.dreamwhether.data.db.DAO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

@HiltViewModel
class ViewModel @Inject constructor(
    private val getWhetherUseCase: GetWhetherUseCase,
    private val dao: DAO
) : ViewModel() {
    private val _state = mutableStateOf(CurrentWhetherState())
    val state: State<CurrentWhetherState> = _state

    private val _cityState = mutableStateOf(emptyList<String>())
    val cityState:State<List<String>> = _cityState

    private val _currentCity = mutableStateOf("")
    val currentCity:State<String> = _currentCity

    private val _citiesForecast = mutableStateOf(emptyList<CurrentWhetherState>())
    val citiesForecast:State<List<CurrentWhetherState>> = _citiesForecast

    private val _showMakeLocationCurrentDialog = mutableStateOf(false)
    val showMakeLocationCurrentDialog:State<Boolean> = _showMakeLocationCurrentDialog

    init {
        addCity( CityEntity(city = "Vladivostok", isSelected = true) )
        observeCurrentCity()
        observeCities()
    }

    private fun getCurrentWhether(location: String, language: String) {
        viewModelScope.launch {
            getWhetherUseCase(location, language).collect{
                when(it){
                    is RequestStates.Error -> _state.value = CurrentWhetherState(error = it.error.toString(), isLoading = false)
                    is RequestStates.Loading -> _state.value = CurrentWhetherState(isLoading = true)
                    is RequestStates.Success -> _state.value = CurrentWhetherState(data = it.data, isLoading = false)
                }
            }
        }
    }
    fun addCity(city: CityEntity) = viewModelScope.launch {
        dao.insertCity(city)
    }
    fun deleteCity(city: CityEntity) = viewModelScope.launch {
        dao.deleteCity(city)
    }
    fun makeCityCurrent(city:String) = viewModelScope.launch {
        dao.makeCityCurrent(city)
    }
    private fun observeCities() {
        viewModelScope.launch {
            dao.getAll().collect { cities ->
                _cityState.value = cities
            }
        }
    }
    private fun observeCurrentCity() = viewModelScope.launch {
        dao.getSelectedCity().collect{
            _currentCity.value = it
            getCurrentWhether(it, "ru")
        }
    }
    fun setShowMakeLocationCurrentDialog(show: Boolean) {
        _showMakeLocationCurrentDialog.value = show
    }
    fun getWhetherForCities() {
        viewModelScope.launch {
            val listOfForecasts = mutableListOf<CurrentWhetherState>()
            cityState.value.forEach { city ->
                getWhetherUseCase(city, "ru").collect { result ->
                    when (result) {
                        is RequestStates.Error -> listOfForecasts.add(CurrentWhetherState(error = result.error.toString(), isLoading = false))
                        is RequestStates.Loading -> listOfForecasts.add(CurrentWhetherState(isLoading = true))
                        is RequestStates.Success -> listOfForecasts.add(CurrentWhetherState(data = result.data, isLoading = false))
                    }
                }
            }
            _citiesForecast.value = listOfForecasts
        }
    }

}
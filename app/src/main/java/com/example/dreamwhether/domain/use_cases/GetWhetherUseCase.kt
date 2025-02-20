package com.example.dreamwhether.domain.use_cases

import android.util.Log
import com.example.dreamwhether.common.RequestStates
import com.example.dreamwhether.data.remoute.dto.WhetherDTO
import com.example.dreamwhether.domain.repository.WhetherRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWhetherUseCase @Inject constructor(private val repository: WhetherRepository) {
    operator fun invoke(location: String, lang: String) = flow {
        try{
            emit(RequestStates.Loading())
            val whether: WhetherDTO = repository.getWhether(location=location, lang = lang)
            emit(RequestStates.Success(data = whether))
        }
        catch (e: Exception){
            Log.e("GetWhetherUseCaseError", e.message.toString())
            emit(RequestStates.Error(error=e.message.toString()))
        }
    }
}
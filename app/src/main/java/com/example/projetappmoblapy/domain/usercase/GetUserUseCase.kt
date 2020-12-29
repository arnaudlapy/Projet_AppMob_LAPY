package com.example.projetappmoblapy.domain.usercase

import com.example.projetappmoblapy.data.repository.UserRepository
import com.example.projetappmoblapy.domain.entity.User

class GetUserUseCase(private val userRepository: UserRepository) {
    suspend fun invoke(email: String) : User?{
        return userRepository.getUser(email)
    }
}
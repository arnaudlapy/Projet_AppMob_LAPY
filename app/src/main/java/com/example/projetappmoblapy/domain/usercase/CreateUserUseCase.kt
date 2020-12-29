package com.example.projetappmoblapy.domain.usercase

import com.example.projetappmoblapy.data.repository.UserRepository
import com.example.projetappmoblapy.domain.entity.User

class CreateUserUseCase(private val userRepository: UserRepository) {
    suspend fun invoke(user: User){
        userRepository.createUser(user)
    }
}
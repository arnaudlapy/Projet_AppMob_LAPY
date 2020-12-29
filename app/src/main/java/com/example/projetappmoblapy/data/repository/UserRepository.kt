package com.example.projetappmoblapy.data.repository

import com.example.projetappmoblapy.data.local.models.DatabaseDao
import com.example.projetappmoblapy.data.local.models.toData
import com.example.projetappmoblapy.data.local.models.toEntity
import com.example.projetappmoblapy.domain.entity.User

class UserRepository(
    private val databaseDao: DatabaseDao) {

    suspend fun createUser(user: User){
        databaseDao.insert(user.toData())
    }

    fun getUser(email: String) : User? {
        val userLocal = databaseDao.findByName(email)
        return userLocal?.toEntity()
    }
}


package com.example.projetappmoblapy.domain.usecase

import com.example.projetappmoblapy.data.repository.UserRepository
import com.example.projetappmoblapy.domain.entity.User
import com.example.projetappmoblapy.domain.usercase.CreateUserUseCase
import com.example.projetappmoblapy.domain.usercase.GetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserUseCaseTest{

    private val userRepository: UserRepository = mockk()
    private val classUnderTest = GetUserUseCase(userRepository)

    @Test
    fun `invoke with invalid email`() {
        runBlocking {
            //GIVEN
            val email = ""
            coEvery { userRepository.getUser(email) } returns null

            //WHEN
            val result = classUnderTest.invoke(email)

            //THEN
            coVerify(exactly = 1) { userRepository.getUser(email) }
            assertEquals(result, null)
        }
    }

    @Test
    fun `invoke with valid email`() {
        runBlocking {
            //GIVEN
            val email = "arnaud@gmail.com"
            val expectedResult = User("arnaud@gmail.com")
            coEvery { userRepository.getUser(email) } returns expectedResult

            //WHEN
            val result = classUnderTest.invoke(email)

            //THEN
            coVerify(exactly = 1) { userRepository.getUser(email) }
            assertEquals(result, expectedResult)
        }
    }
}

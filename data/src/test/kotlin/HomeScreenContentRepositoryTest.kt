package com.sergediame.data

import com.sergediame.data.home.HomeScreenContentDataSource
import com.sergediame.data.home.HomeScreenContentRepositoryImpl
import com.sergediame.domain.entity.HomeScreenContentItem
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class HomeScreenContentRepositoryTest {

    private val dataSource = mockk<HomeScreenContentDataSource>()
    private val iODispatcher = Dispatchers.Unconfined
    private val repository = HomeScreenContentRepositoryImpl(dataSource, iODispatcher)

    @Test
    fun `test getHomeScreenContent`() = runTest {
        // Given
        val expectedContent = listOf<HomeScreenContentItem>()
        coEvery { dataSource.getHomeScreenContent() } returns expectedContent

        // When
        val actualContent = repository.content.first()

        // Then
        assertEquals(expectedContent, actualContent)
    }
}

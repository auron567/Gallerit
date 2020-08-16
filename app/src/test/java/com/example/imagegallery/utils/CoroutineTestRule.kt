package com.example.imagegallery.utils

import com.example.imagegallery.contextprovider.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class CoroutineTestRule(
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher(),
    TestCoroutineScope by TestCoroutineScope(testDispatcher) {

    val testDispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher get() = testDispatcher

        override val default: CoroutineDispatcher get() = testDispatcher

        override val io: CoroutineDispatcher get() = testDispatcher

        override val unconfined: CoroutineDispatcher get() = testDispatcher
    }

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}

package com.sorcoz.andorid

import com.sorcoz.domain.IDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProvider @Inject constructor() : IDispatcherProvider {

    override val io: CoroutineDispatcher = Dispatchers.IO

    override val default: CoroutineDispatcher = Dispatchers.Default

    override val ui: CoroutineDispatcher = Dispatchers.Main
}
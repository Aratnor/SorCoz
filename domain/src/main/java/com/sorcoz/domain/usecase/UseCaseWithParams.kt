package com.sorcoz.domain.usecase

abstract class UseCaseWithParams<in Params, out R> {

    suspend fun execute(params: Params): R = buildUseCase(params)

    protected abstract suspend fun buildUseCase(params: Params): R
}
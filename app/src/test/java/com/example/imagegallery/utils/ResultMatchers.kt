package com.example.imagegallery.utils

import com.example.imagegallery.data.model.Result
import io.kotest.matchers.Matcher
import io.kotest.matchers.MatcherResult
import io.kotest.matchers.should

// Custom Kotest matchers for the Result class.

fun <T> Result<T>.shouldBeSuccess(expected: T) = this should beSuccess(expected)

fun <T> beSuccess(expected: T) = object : Matcher<Result<T>> {
    override fun test(value: Result<T>): MatcherResult {
        return when (value) {
            !is Result.Success<T> -> MatcherResult(
                false,
                "Result should be Success.",
                "Result should not be Success."
            )
            else -> MatcherResult(
                value.data == expected,
                "Result should be Success($expected), but instead got Succes(${value.data}).",
                "Result should not be Success($expected)."
            )
        }
    }
}

fun <T> Result<T>.shouldBeError() = this should beError()

fun <T> beError() = object : Matcher<Result<T>> {
    override fun test(value: Result<T>): MatcherResult {
        return MatcherResult(
            value is Result.Error,
            "Result should be Error.",
            "Result should not be Error."
        )
    }
}

fun <T> Result<T>.shouldBeEmpty() = this should beEmpty()

fun <T> beEmpty() = object : Matcher<Result<T>> {
    override fun test(value: Result<T>): MatcherResult {
        return MatcherResult(
            value is Result.Empty,
            "Result should be Empty.",
            "Result should not be Empty."
        )
    }
}

fun <T> Result<T>.shouldBeLoading() = this should beLoading()

fun <T> beLoading() = object : Matcher<Result<T>> {
    override fun test(value: Result<T>): MatcherResult {
        return MatcherResult(
            value is Result.Loading,
            "Result should be Loading.",
            "Result should not be Loading."
        )
    }
}

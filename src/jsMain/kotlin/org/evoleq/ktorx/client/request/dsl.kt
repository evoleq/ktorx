package org.evoleq.ktorx.client.request

import io.ktor.client.HttpClient
import kotlinx.coroutines.coroutineScope
import org.evoleq.math.cat.monad.result.Result
import org.evoleq.math.cat.suspend.morphism.ScopedSuspended
import org.evoleq.math.cat.suspend.morphism.by

suspend fun <I, O> HttpClient.byRequest(
    request: Request<I, O>
): ScopedSuspended<I, Pair<Result<O, Throwable>, HttpClient>> =
    ScopedSuspended{
            i -> by(by(request)(i))(this@byRequest)
    }

suspend fun <I, O> ScopedSuspended<I, Pair<Result<O, Throwable>, HttpClient>>.on(input: I): Pair<Result<O, Throwable>, HttpClient> =
    coroutineScope{ by(this@on)(input)}

package org.evoleq.ktorx.client.request

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import kotlinx.serialization.KSerializer
import org.drx.evoleq.type.KlScopedSuspendedState
import org.evoleq.math.cat.monad.result.Result

typealias Request<I, O> = KlScopedSuspendedState<HttpClient, I, Result<O, Throwable>>



// ??? Needed ???
sealed class RequestData<Data>(
    open val dataSerializer: KSerializer<Data>,
    open val urlString: String,
    open val block: HttpRequestBuilder.()->Unit
) {
    data class Get<Data>(
        override val dataSerializer: KSerializer<Data>,
        override val urlString: String,
        override val block: HttpRequestBuilder.()->Unit
    ) : RequestData<Data>(
        dataSerializer,
        urlString,
        block
    )
}
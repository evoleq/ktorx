/**
 * Copyright (c) 2020 Dr. Florian Schmidt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.evoleq.ktorx.client.request

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import kotlinx.serialization.KSerializer
import org.evoleq.math.cat.suspend.monad.result.Result
import org.evoleq.math.cat.suspend.monad.state.KlScopedSuspendedState

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
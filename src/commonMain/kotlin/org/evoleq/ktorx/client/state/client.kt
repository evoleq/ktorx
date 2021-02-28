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
package org.evoleq.ktorx.client.state

import io.ktor.client.*
import kotlinx.coroutines.CoroutineScope
import org.evoleq.ktorx.marker.KtorxDsl
import org.evoleq.math.cat.suspend.monad.state.KlScopedSuspendedState
import org.evoleq.math.cat.suspend.monad.state.ScopedSuspendedState

typealias HttpClientState<T> = ScopedSuspendedState<HttpClient, T>
typealias KlHttpClientState<S, T> = KlScopedSuspendedState<HttpClient, S, T>

@Suppress("FunctionName")
@KtorxDsl
fun <T> HttpClientState(
    state: suspend CoroutineScope.(HttpClient)->Pair<T, HttpClient>
): HttpClientState<T> =
    ScopedSuspendedState{
            session -> state(session)
    }

@KtorxDsl
@Suppress("FunctionName")
fun <S, T> KLHttpClientState(
    kleisli: suspend CoroutineScope.(S)-> HttpClientState<T>
): KlHttpClientState<S, T> =
    KlScopedSuspendedState{
            data -> kleisli(data)
    }
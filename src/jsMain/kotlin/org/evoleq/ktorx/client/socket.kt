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
package org.evoleq.ktorx.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.features.websocket.WebSockets
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.CoroutineScope
import org.evoleq.math.cat.suspend.monad.state.KlScopedSuspendedState
import org.evoleq.math.cat.suspend.monad.state.ScopedSuspendedState

@KtorExperimentalAPI
val client = HttpClient(Js){
    install(WebSockets)
}

typealias WebSocketSessionState<T> = ScopedSuspendedState<WebSocketSession, T>
typealias KLWebSocketSessionState<S, T> = KlScopedSuspendedState<WebSocketSession, S, T>


fun <T> WebSocketSessionState(
    state: suspend CoroutineScope.(WebSocketSession)->Pair<T, WebSocketSession>
): WebSocketSessionState<T> =
    ScopedSuspendedState{
        session -> state(session)
    }

fun <S, T> KLWebSocketSessionState(
    kleisli: suspend CoroutineScope.(S)-> WebSocketSessionState<T>
): KLWebSocketSessionState<S, T> =
    KlScopedSuspendedState{
        data -> kleisli(data)
    }
package org.evoleq.ktorx.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.features.websocket.WebSockets
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.CoroutineScope
import org.drx.evoleq.type.KlScopedSuspendedState
import org.drx.evoleq.type.ScopedSuspendedState

@KtorExperimentalAPI
val client = HttpClient(Js){
    install(WebSockets)
}

typealias WebSocketSessionState<T> = ScopedSuspendedState<WebSocketSession, T>
typealias KLWebSocketSessionState<S, T> = KlScopedSuspendedState<WebSocketSession,S, T>


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
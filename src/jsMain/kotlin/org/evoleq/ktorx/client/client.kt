package org.evoleq.ktorx.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

suspend fun <T> withClient(request: suspend HttpClient.()->T): T = with(HttpClient(Js) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}) {
    request()
}
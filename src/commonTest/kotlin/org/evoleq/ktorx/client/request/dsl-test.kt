package org.evoleq.ktorx.client.request

import io.ktor.client.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.evoleq.ktorx.client.state.HttpClientState
import org.evoleq.ktorx.client.state.KLHttpClientState
import org.evoleq.math.cat.structure.x
import org.evoleq.math.cat.suspend.monad.result.ResultT
import org.evoleq.math.cat.suspend.monad.result.retT
import kotlin.test.Test

class DslTest {
    
    @Test
    fun dslDoTypesMatch() {
        GlobalScope.launch {
            with(HttpClient()) {
                byRequest(KLHttpClientState { number: Int ->
                    HttpClientState { client ->
                        ResultT.retT(number) x client
                    }
                }) on 2
            }
        }
    }
    
}
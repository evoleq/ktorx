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

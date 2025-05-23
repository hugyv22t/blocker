/*
 * Copyright 2025 Blocker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.merxury.blocker.core.data.respository.licenses

import com.merxury.blocker.core.data.licenses.fetcher.LicensesFetcher
import com.merxury.blocker.core.dispatchers.BlockerDispatchers.IO
import com.merxury.blocker.core.dispatchers.Dispatcher
import com.merxury.blocker.core.model.licenses.LicenseItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class LocalLicensesRepository @Inject constructor(
    private val licensesFetcher: LicensesFetcher,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : LicensesRepository {

    override fun getLicensesList(): Flow<List<LicenseItem>> = flow {
        emit(licensesFetcher.fetch())
    }
        .flowOn(ioDispatcher)
}

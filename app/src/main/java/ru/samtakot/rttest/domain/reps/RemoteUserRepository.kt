package ru.samtakot.rttest.domain.reps

import io.reactivex.Observable
import ru.samtakot.rttest.domain.dto.UserPage

interface RemoteUserRepository {

    fun retrieveMoreEmployees(nextPageNum: Int): Observable<UserPage>
}
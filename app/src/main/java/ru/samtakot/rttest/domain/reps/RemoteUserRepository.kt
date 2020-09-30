package ru.samtakot.rttest.domain.reps

import io.reactivex.Observable
import ru.samtakot.rttest.domain.dto.UserPage

interface RemoteUserRepository {

    fun retrieveMoreUsers(nextPageNum: Int): Observable<UserPage>
}
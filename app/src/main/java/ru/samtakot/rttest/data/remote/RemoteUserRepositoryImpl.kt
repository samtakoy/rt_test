package ru.samtakot.rttest.data.remote

import io.reactivex.Observable
import ru.samtakot.rttest.data.remote.api.RequestApi
import ru.samtakot.rttest.domain.dto.UserPage
import ru.samtakot.rttest.domain.reps.RemoteUserRepository
import javax.inject.Inject

class RemoteUserRepositoryImpl @Inject constructor(
    @Inject
    var api: RequestApi
): RemoteUserRepository {

    override fun retrieveMoreEmployees(nextPageNum: Int): Observable<UserPage> {
        return api.getUsers(nextPageNum)
            .map{it.toDomainEntity()}
    }

}
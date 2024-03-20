package com.kgignatyev.donations.service.api_impl


import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Status
import org.springframework.http.ResponseEntity


object V1StatusHelpers {
    const val STATUS_ERROR = "ERROR"
    const val STATUS_OK = "OK"
    val OK = V1Status().responseCode(STATUS_OK)
    val OK_ENTITY = ResponseEntity.ok(OK)
}

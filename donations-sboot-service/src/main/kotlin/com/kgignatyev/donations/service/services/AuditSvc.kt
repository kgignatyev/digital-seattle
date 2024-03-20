package com.kgignatyev.donations.service.services

import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Distribution
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Donation
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Donor
import org.springframework.stereotype.Service
import java.time.OffsetDateTime


@Service
class AuditSvc {

    //todo: consider using reflection, or extension functions
    fun stampCreateAudit(v: Any) {
        when (v) {
            is V1Donation -> {
                v.createdAt = OffsetDateTime.now()
                v.createdBy = currentUserId()
            }
            is V1Distribution -> {
                v.createdAt = OffsetDateTime.now()
                v.createdBy = currentUserId()
            }
            is V1Donor ->{
                v.createdAt = OffsetDateTime.now()
                v.createdBy = currentUserId()
            }

            else -> {
                TODO("Implement handling for ${v.javaClass}")
            }
        }
    }

    private fun currentUserId(): String {
        //todo: implement properly
        return "root"
    }
}

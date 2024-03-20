package com.kgignatyev.donations.service.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Donor
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1DonorsList
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1ListSummary
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1SearchRequest
import org.springframework.stereotype.Service

@Service
class DonorsSvc(om:ObjectMapper, auditSvc: AuditSvc):CRUDSvc<V1Donor>( auditSvc, SimpleStorage(V1Donor::class, om)) {


    fun search(v1SearchRequest: V1SearchRequest): V1DonorsList {
        val txt = v1SearchRequest.textCriteria
        val matching = storage.list().filter {
            it.name.contains(txt) || it.email.contains(txt) || it.phone.contains(txt)
        }
        val r = V1DonorsList()
        r.items = matching
        r.summary = V1ListSummary(matching.size.toLong(), 0)
        return r
    }


    fun listDonors(): V1DonorsList {
        val allDonors = storage.list()
        val r = V1DonorsList()
        r.items = allDonors
        r.summary = V1ListSummary(allDonors.size.toLong(),0)
        return r
    }
}

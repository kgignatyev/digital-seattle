package com.kgignatyev.donations.service.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Distribution
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1DistributionsList
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1ListSummary
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1SearchRequest
import org.springframework.stereotype.Service

@Service
class DistributionSvc(om: ObjectMapper, auditSvc: AuditSvc):CRUDSvc<V1Distribution>( auditSvc, SimpleStorage(V1Distribution::class, om)) {
    fun searchDistributions(v1SearchRequest: V1SearchRequest): V1DistributionsList {
        //todo: implement propertly
        val distributions = listAll()
        val res = V1DistributionsList()
        res.items = distributions
        res.summary = V1ListSummary(distributions.size.toLong(), 0)
        return res
    }
}

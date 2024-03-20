package com.kgignatyev.donations.service.api_impl

import com.kgignatyev.donations.service.services.DistributionSvc
import com.kgignatyev.donations_svc.api.donationssvc.DistributionsServiceV1Api
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1DistributionsList
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1SearchRequest
import jakarta.annotation.Resource
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/api"])
@CrossOrigin(
    origins = ["*"],
    allowedHeaders = ["*"],
    methods = [RequestMethod.PATCH, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.PUT]
)
@RestController
class DistributionsServiceV1ApiImpl : DistributionsServiceV1Api {

    @Resource
    lateinit var distributionSvc: DistributionSvc
    override fun searchDistributions(v1SearchRequest: V1SearchRequest): ResponseEntity<V1DistributionsList> {

        return ok(distributionSvc.searchDistributions(v1SearchRequest))
    }
}

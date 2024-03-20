package com.kgignatyev.donations.service.api_impl

import com.kgignatyev.donations.service.services.DonationsSvc
import com.kgignatyev.donations_svc.api.donationssvc.DonationsServiceV1Api
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Donation
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1DonationsList
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
class DonationsServiceV1ApiImpl : DonationsServiceV1Api {

    @Resource
    lateinit var donationsSvc: DonationsSvc

    override fun createDonation(v1Donation: V1Donation): ResponseEntity<V1Donation> {
        val d = donationsSvc.create(v1Donation)
        return ok(d)
    }

    override fun searchDonations(v1SearchRequest: V1SearchRequest): ResponseEntity<V1DonationsList> {
        return ok(donationsSvc.searchDonations(v1SearchRequest))
    }
}

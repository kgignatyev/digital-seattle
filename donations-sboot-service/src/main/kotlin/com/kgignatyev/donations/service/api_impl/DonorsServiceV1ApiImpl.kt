package com.kgignatyev.donations.service.api_impl

import com.kgignatyev.donations.service.services.DonorsSvc
import com.kgignatyev.donations_svc.api.donationssvc.DonationsServiceV1Api
import com.kgignatyev.donations_svc.api.donationssvc.DonorsServiceV1Api
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Donor
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1DonorsList
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
class DonorsServiceV1ApiImpl:DonorsServiceV1Api {

    @Resource
    lateinit var donorsSvc:DonorsSvc

    override fun createDonor(v1Donor: V1Donor): ResponseEntity<V1Donor> {
        return ok(donorsSvc.create(v1Donor))
    }

    override fun getDonor(id: String): ResponseEntity<V1Donor> {
        return ok(donorsSvc.getById(id))
    }

    override fun listDonors(): ResponseEntity<V1DonorsList> {
        return ok(donorsSvc.listDonors())
    }

    override fun searchDonors(v1SearchRequest: V1SearchRequest): ResponseEntity<V1DonorsList> {
        return ok(donorsSvc.search(v1SearchRequest))
    }

    override fun updateDonor(id: String, v1Donor: V1Donor): ResponseEntity<V1Donor> {
        return super.updateDonor(id, v1Donor)
    }
}

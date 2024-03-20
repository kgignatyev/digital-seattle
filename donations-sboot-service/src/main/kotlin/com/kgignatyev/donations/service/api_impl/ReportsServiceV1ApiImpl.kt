package com.kgignatyev.donations.service.api_impl

import com.kgignatyev.donations.service.services.DonationsSvc
import com.kgignatyev.donations.service.services.DonorsSvc
import com.kgignatyev.donations.service.services.InventorySvc
import com.kgignatyev.donations_svc.api.donationssvc.ReportsServiceV1Api
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Commodity
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1DonorContribution
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
class ReportsServiceV1ApiImpl: ReportsServiceV1Api {

    @Resource
    lateinit var invSvc: InventorySvc


    @Resource
    lateinit var donationSvc: DonationsSvc

    override fun inventoryReport(): ResponseEntity<List<V1Commodity>> {
        return ok( invSvc.currentInventory())
    }

    override fun donatorsReport(): ResponseEntity<List<V1DonorContribution>> {
        return ok( donationSvc.donatorsReport())
    }
}

package com.kgignatyev.donations.service.services

import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Commodity
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1CommodityType
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Distribution
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Donation
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Donor
import org.springframework.stereotype.Service
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class TestDataSvc(
    val donorsSvc: DonorsSvc, val donationsSvc: DonationsSvc,
    val distrSvc: DistributionSvc, val inventorySvc: InventorySvc
) {

    fun createSmokeTestData() {

        if (donorsSvc.count() == 0) {
            (0..9).forEach { d ->
                donorsSvc.create(makeTestDonor(d))
            }
        }

        if (donationsSvc.count() == 0) {
            makeTestDonations(donorsSvc.listDonors().items).forEach {
                donationsSvc.create(it)
            }
        }

        if (distrSvc.count() == 0) {
            makeTestDistributions()
        }

    }

    private fun makeTestDistributions() {
        val numDistributions = 10
        (1..numDistributions).forEach {
            makeTestDistribution()
        }
    }

    private fun makeTestDistribution() {
        val inventory = inventorySvc.currentInventory().filter { it.amount > 0 }
        val dist = V1Distribution()
        dist.recipientId = "recipient_1"
        val numCommodities = Random.nextInt(1..inventory.size)
        (0..<numCommodities).forEach { cIdx ->
            val commodity = inventory[cIdx]
            val upperBound = commodity.amount.toInt() / 10
            if (upperBound > 1) {
                val amount = Random.nextInt(1, upperBound)
                val c = V1Commodity()
                c.name = commodity.name
                c.amount = amount.toDouble()
                c.amountKind = commodity.amountKind
                c.commodityType = commodity.commodityType
                dist.addCommoditiesItem(c)
            }
        }
        if ( dist.commodities.isNotEmpty()){
            distrSvc.create( dist )
        }

    }

    private fun makeTestDonations(donors: List<V1Donor>): MutableList<V1Donation> {
        val res = mutableListOf<V1Donation>()
        donors.forEachIndexed { i, donor ->
            val numDonations = Random.nextInt(3..10)
            (0..numDonations).forEach {
                val d = V1Donation()
                d.donorId = donor.id
                (0..Random.nextInt(1, 4)).forEach {
                    val randomTypeIndex = Random.nextInt(V1CommodityType.entries.size)
                    val commodityType = V1CommodityType.entries[randomTypeIndex]
                    val commodity = createTestCommodityOf(commodityType)
                    d.addCommoditiesItem(commodity)
                    res.add(d)
                }
            }
        }
        return res
    }

    private fun createTestCommodityOf(commodityType: V1CommodityType): V1Commodity {
        val c = V1Commodity()
        c.commodityType = commodityType
        when (commodityType) {
            V1CommodityType.MONEY -> {
                c.name = "money"
                c.amount = Random.nextInt(10, 200).toDouble()
                c.amountKind = "USD"
            }

            V1CommodityType.GOODS -> {
                c.name = "cat litter"
                c.amount = Random.nextInt(1, 20).toDouble()
                c.amountKind = "pound"
            }

            V1CommodityType.FOOD -> {
                c.name = "dog food"
                c.amount = Random.nextInt(1, 20).toDouble()
                c.amountKind = "pound"
            }
        }

        return c
    }

    private fun makeTestDonor(d: Int): V1Donor {
        val r = V1Donor()
        r.name = "Donor $d"
        r.email = "donor$d@testmail.com"
        r.phone = "425.123.231$d"
        return r
    }
}

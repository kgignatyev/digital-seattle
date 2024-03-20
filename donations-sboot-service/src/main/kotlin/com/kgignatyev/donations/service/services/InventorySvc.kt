package com.kgignatyev.donations.service.services

import com.kgignatyev.donations.service.services.InventoryHelper.combineCommodities
import com.kgignatyev.donations.service.services.InventoryHelper.keyOf
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.V1Commodity
import org.springframework.stereotype.Service

object InventoryHelper {
    fun combineCommodities(list: List<V1Commodity>): Map<String, V1Commodity> {
        val res = mutableMapOf<String, V1Commodity>()
        list.forEach { c ->
            val k = keyOf(c)
            val currentSummary = res[k]
            if (currentSummary == null) {
                res[k] = V1Commodity(c.name, c.amount, c.amountKind, c.commodityType)
            } else {
                currentSummary.amount += c.amount
            }
        }
        return res
    }

    fun keyOf(c: V1Commodity): String {
        return "${c.commodityType}_${c.amountKind}_${c.name}"
    }
}


@Service
class InventorySvc(val donationsSvc: DonationsSvc, val distSvc: DistributionSvc) {


    fun currentInventory(): List<V1Commodity> {
        val allDonations = donationsSvc.listAll()
        val combinedDonations = combineCommodities(allDonations.map { it.commodities }.flatten())
        val distributions = distSvc.listAll().map { it.commodities }.flatten()
        distributions.forEach { cOut ->
            val cInv = combinedDonations[keyOf(cOut)]!!
            cInv.amount -= cOut.amount
        }
        return combinedDonations.values.sortedBy { it.name }.toList()
    }
}

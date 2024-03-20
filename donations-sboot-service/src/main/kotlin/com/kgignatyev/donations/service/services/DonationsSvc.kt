package com.kgignatyev.donations.service.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.kgignatyev.donations.service.services.InventoryHelper.combineCommodities
import com.kgignatyev.donations_svc.api.donationssvc.v1.model.*
import org.springframework.stereotype.Service

@Service
class DonationsSvc(val donorsSvc: DonorsSvc,om:ObjectMapper, auditSvc: AuditSvc):CRUDSvc<V1Donation>( auditSvc, SimpleStorage(V1Donation::class, om)) {

    fun donatorsReport(): List<V1DonorContribution> {
        val allDonations = storage.list()
        val donationsByDonors = allDonations.groupBy { it.donorId }
        val donors = donorsSvc.listDonors().items.sortedBy { it.name }
        return donors.map { d ->
            val dc = V1DonorContribution()
            dc.donor = d
            val donationsByDonor = donationsByDonors[d.id]
            val combinedDonations:List<V1Commodity> =  if( donationsByDonor==null){
                emptyList()
            }else{
                val allCommodities = donationsByDonor.map { it.commodities }.flatten()
                combineCommodities( allCommodities ).values.toList().sortedBy { it.name }
            }
            dc.contributions= combinedDonations
            dc
        }
    }

    fun searchDonations(v1SearchRequest: V1SearchRequest): V1DonationsList {
        //todo: implement propertly
        val donations = listAll()
        val res = V1DonationsList()
        res.items = donations
        res.summary = V1ListSummary(donations.size.toLong(), 0)
        return res
    }


}

package com.kgignatyev.donations.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.kgignatyev.donations.service.services.DonorsSvc
import com.kgignatyev.donations.service.services.TestDataSvc
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class DonationsService {

    companion object{

        lateinit var cxt:ConfigurableApplicationContext



        @JvmStatic
        fun main(args: Array<String>) {
            cxt = SpringApplication.run(DonationsService::class.java, *args)
            //for simplification, we will initialize test data if none
            initTestData(cxt)
        }

        private fun initTestData(cxt: ConfigurableApplicationContext) {
            val testDataSvc = cxt.getBean(TestDataSvc::class.java)
            testDataSvc.createSmokeTestData()
        }


    }
}

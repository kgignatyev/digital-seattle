package com.kgignatyev.donations.service.services

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


open class CRUDSvc<T:Any>(val auditSvc: AuditSvc, val storage: SimpleStorage<T>) {

    private  val  propsCache = mutableMapOf<KClass<*>,MutableMap<String, KProperty1<Any, Any>>>()
    private fun <T : Any> idProperty(clazz: KClass<T>): KMutableProperty1<Any, String> {
        val klazzProps = propsCache.getOrPut(clazz, { mutableMapOf() })
        return klazzProps.getOrPut("id") {
            val p = clazz.memberProperties.first { it.name == "id" } as KMutableProperty1<Any, Any>
            p.isAccessible = true
            p
        } as KMutableProperty1<Any, String>
    }

    fun create(v: T): T {
        val idP = idProperty( v::class)
        idP.set( v,storage.nextStringId())
        auditSvc.stampCreateAudit(v)
        storage.store(v)
        return v
    }

    fun getById(id: String): T {
        val idP = idProperty( storage.itemsClass)
        return storage.list().find { idP.get( it ) == id }!!
    }

    fun count(): Int {
        return storage.count()
    }

    fun listAll(): List<T> {
        return storage.list()
    }
}

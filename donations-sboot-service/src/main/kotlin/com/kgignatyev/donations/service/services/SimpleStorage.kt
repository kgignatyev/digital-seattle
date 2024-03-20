package com.kgignatyev.donations.service.services

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File
import kotlin.reflect.KClass

interface Identifiable {
    fun getId():String?
    fun setId(v:String)
}

object StorageHelper {
    val identifiableClass = Identifiable::class
}


class SimpleStorage<T:Any>(val itemsClass: KClass<T>, val om:ObjectMapper) {
    private val storageFile:File = File("storage/${itemsClass.simpleName}.json")
    private var items = mutableListOf<T>()


    init {
        val parentDir = storageFile.parentFile
        if( !parentDir.exists()){
            parentDir.mkdirs()
        }
        if( storageFile.exists() ){
           val listReader = om.readerForListOf(itemsClass.java )
           val list = listReader.readValue<List<T>>( storageFile)
           items.addAll(list)
        }
    }


    fun store(v:T):T {
        items.add(v)
        persist()
        return v
    }

    fun persist(){
        synchronized(this){
            om.writeValue( storageFile, items)
        }
    }

    fun nextStringId(): String {
        return System.nanoTime().toString()
    }

    fun count(): Int {
        return items.size
    }

    fun list(): List<T> {
        return items
    }


}

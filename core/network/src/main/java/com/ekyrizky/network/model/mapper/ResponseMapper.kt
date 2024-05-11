package com.ekyrizky.network.model.mapper

interface ResponseMapper<Response, Domain> {

    fun asDomain(response: Response): Domain
}
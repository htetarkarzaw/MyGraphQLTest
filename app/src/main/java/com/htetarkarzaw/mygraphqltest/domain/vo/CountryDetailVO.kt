package com.htetarkarzaw.mygraphqltest.domain.vo

data class CountryDetailVO(
    val code:String,
    val name:String,
    val emoji:String,
    val capital:String,
    val currency:String,
    val languages:List<String>,
    val continent:String,

)
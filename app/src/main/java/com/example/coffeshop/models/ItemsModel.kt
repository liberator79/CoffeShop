package com.example.coffeshop.Domain

data class ItemsModel(
    var title : String = "",
    var description : String = "",
    var picUrl: MutableList<String> = ArrayList(),
    var price : Double = 0.0,
    var rating : Double = 0.0,
    var numberInCart : Int = 0,
    var extra : String = "",
)

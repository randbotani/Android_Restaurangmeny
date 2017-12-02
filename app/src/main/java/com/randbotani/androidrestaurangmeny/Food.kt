package com.randbotani.androidrestaurangmeny

/**
 * Created by Rand Botani on 2017-11-29.
 */
class Food{
    var name:String? = null
    var des:String? = null
    var image:Int? = null


    constructor(name: String, des: String, image: Int) {
        this.name = name
        this.des = des
        this.image = image
    }


}
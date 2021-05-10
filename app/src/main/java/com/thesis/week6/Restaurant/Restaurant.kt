package com.thesis.week6.Restaurant

class Restaurant (val name:String, val address : String, val avatar : String, var fav : Boolean) {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}
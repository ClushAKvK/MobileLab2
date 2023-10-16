package ru.example.mobiledevelopment.carviewer.data

import java.io.Serializable

class CarParameters(val id: Int, val carName: String, val yearProd: String, val numOfDoors : String,
                    val curbWeight: String?, val maxSpeed : String?, val trunkVolume : String?, val minTruingRad: String?,
                    val addictionInfo: String?): Serializable

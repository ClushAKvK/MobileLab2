package ru.example.mobiledevelopment.carviewer.models

import android.util.Log
import androidx.lifecycle.ViewModel
import ru.example.mobiledevelopment.carviewer.data.CarParameters

class CarModel: ViewModel() {
    val carBank = mutableListOf<CarParameters>()

    var tempCarIdx = -1

    var carName : String? = null
    var yearProd : String? = null
    var numOfDoors : String? = null
    var curbWeight : String? = null
    var maxSpeed : String? = null
    var trunkVolume : String? = null
    var minTruingRad : String? = null
    var addictionInfo : String? = null

    var hasView: Boolean = false

    private fun updateTempCarData() {
        if (tempCarIdx >= 0 && tempCarIdx <= carBank.size) {
            hasView = true
            carName = carBank[tempCarIdx].carName
            yearProd = carBank[tempCarIdx].yearProd
            numOfDoors = carBank[tempCarIdx].numOfDoors
            curbWeight = carBank[tempCarIdx].curbWeight
            maxSpeed = carBank[tempCarIdx].maxSpeed
            trunkVolume = carBank[tempCarIdx].trunkVolume
            minTruingRad = carBank[tempCarIdx].minTruingRad
            addictionInfo = carBank[tempCarIdx].addictionInfo
        }
        else {
            hasView = false
        }
    }


    fun getNextCar() {
        if (tempCarIdx + 1 < carBank.size)
            tempCarIdx += 1
        else
            tempCarIdx = 0

        updateTempCarData()
    }

    fun getPrevCar() {
        if (tempCarIdx - 1 >= 0)
            tempCarIdx -= 1
        else
            tempCarIdx = carBank.size - 1
        updateTempCarData()
    }

    fun getCarBy(idx: Int) {
        tempCarIdx = idx
        updateTempCarData()
    }

    fun addCar(newCar: CarParameters) {
        carBank.add(newCar)
        getCarBy(carBank.size - 1)
        Log.d("CarModel", "carBank: $carBank")
    }

    fun updateCar(car: CarParameters) {
        carBank[tempCarIdx] = car
    }

    fun deleteCar() {
        if (carBank.isNotEmpty()) {
            carBank.removeAt(tempCarIdx)
            getPrevCar()
        }
    }

    override fun toString(): String {
        var info = ""
        if (hasView) {
            info =
                "Название: ${carName}\n" +
                        "Год выпуска: ${yearProd}\n" +
                        "Количество дверей/мест: ${numOfDoors}\n" +
                        "Максимальная скорость: ${maxSpeed} км/ч\n" +
                        (if (curbWeight != "") "Снаряженная масса: ${curbWeight} кг\n" else "") +
                        (if (trunkVolume != "") "Объем багажника min/max: ${trunkVolume} л\n" else "") +
                        (if (minTruingRad != "") "Минимальный радиус поворота: ${minTruingRad} м\n" else "") +
                        "--------------------------\n" +
                        if (addictionInfo != "") "Дополнительная информация:\n${addictionInfo}" else ""
        }
        else {
            info = "Вы ещё не добавили ни одной машины..."
        }
        return info
    }

}
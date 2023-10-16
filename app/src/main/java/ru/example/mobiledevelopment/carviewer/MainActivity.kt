package ru.example.mobiledevelopment.carviewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import ru.example.mobiledevelopment.carviewer.data.CarParameters
import ru.example.mobiledevelopment.carviewer.models.CarModel
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var tvCarInfo: TextView
    private lateinit var btnPrevCar: Button
    private lateinit var btnNextCar: Button
    private lateinit var btnEdit: Button
    private lateinit var btnAdd: Button
    private lateinit var btnDel: Button

    private val viewModel: CarModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(CarModel::class.java)
    }

    private val addCarLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val car = data?.getSerializableExtra("result") as? CarParameters
            car?.let {
                viewModel.addCar(it)
                updateCarInfo()
            }
        }
    }

    private val editCarLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val car = data?.getSerializableExtra("result") as? CarParameters
            car?.let {
                viewModel.updateCar(it)
                updateCarInfo()
            }
        }
    }

    private fun updateCarInfo() {
        tvCarInfo.text = viewModel.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCarInfo = findViewById(R.id.tvCarInfo)
        btnPrevCar = findViewById(R.id.btnPrevCar)
        btnNextCar = findViewById(R.id.btnNextCar)
        btnEdit = findViewById(R.id.btnEdit)
        btnAdd = findViewById(R.id.btnAdd)
        btnDel = findViewById(R.id.btnDel)

        btnPrevCar.setOnClickListener {
            viewModel.getPrevCar()
            updateCarInfo()
        }

        btnNextCar.setOnClickListener {
            viewModel.getNextCar()
            updateCarInfo()
        }

        btnAdd.setOnClickListener {
            val intent = Intent(this, EditCarActivity::class.java)
            addCarLauncher.launch(intent)
        }

        btnEdit.setOnClickListener {
            if (viewModel.carBank.size != 0) {
                val intent = Intent(this, EditCarActivity::class.java)
                intent.putExtra("name", viewModel.carName)
                intent.putExtra("period", viewModel.yearProd)
                intent.putExtra("maxSpeed", viewModel.maxSpeed)
                intent.putExtra("numOfDoors", viewModel.numOfDoors)
                intent.putExtra("curbWeight", viewModel.curbWeight)
                intent.putExtra("trunkVolume", viewModel.trunkVolume)
                intent.putExtra("minTurningRadius", viewModel.minTruingRad)
                intent.putExtra("additional", viewModel.addictionInfo)
                editCarLauncher.launch(intent)
            }
        }

        btnDel.setOnClickListener {
            viewModel.deleteCar()
            updateCarInfo()
        }
    }

}
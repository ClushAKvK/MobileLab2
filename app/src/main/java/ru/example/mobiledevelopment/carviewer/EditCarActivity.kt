package ru.example.mobiledevelopment.carviewer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import ru.example.mobiledevelopment.carviewer.data.CarParameters

class EditCarActivity : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edPeriod: EditText
    private lateinit var edNumOfDoors: EditText
    private lateinit var edCurbWeight: EditText
    private lateinit var edMaxSpeed: EditText
    private lateinit var edTrunkVolume: EditText
    private lateinit var edMinTurningRadius: EditText
    private lateinit var edAdditional: EditText

    private lateinit var btnCancel: Button
    private lateinit var btnAddCar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_car)

        edName = findViewById(R.id.edName)
        edPeriod = findViewById(R.id.edPeriod)
        edNumOfDoors = findViewById(R.id.edNumOfDoors)
        edMaxSpeed = findViewById(R.id.edMaxSpeed)
        edCurbWeight = findViewById(R.id.edCurbWeight)
        edTrunkVolume = findViewById(R.id.edTrunkVolume)
        edMinTurningRadius = findViewById(R.id.edMinTurningRadius)
        edAdditional = findViewById(R.id.edAdditional)

        btnCancel = findViewById(R.id.btnCancel)
        btnAddCar = findViewById(R.id.btnAddCar)


        edName.setText(intent.getStringExtra("name") ?: "")
        edPeriod.setText(intent.getStringExtra("period") ?: "")
        edNumOfDoors.setText(intent.getStringExtra("numOfDoors") ?: "")
        edMaxSpeed.setText(intent.getStringExtra("maxSpeed") ?: "")
        edCurbWeight.setText(intent.getStringExtra("curbWeight") ?: "")
        edTrunkVolume.setText(intent.getStringExtra("trunkVolume") ?: "")
        edMinTurningRadius.setText(intent.getStringExtra("minTurningRadius") ?: "")
        edAdditional.setText(intent.getStringExtra("additional") ?: "")


        btnCancel.setOnClickListener {
            finish()
        }

        btnAddCar.setOnClickListener {
            if (checkAttributes()) {
                val result = CarParameters(
                    1,
                    edName.text.toString(),
                    edPeriod.text.toString(),
                    edNumOfDoors.text.toString(),
                    edCurbWeight.text.toString(),
                    edMaxSpeed.text.toString(),
                    edTrunkVolume.text.toString(),
                    edMinTurningRadius.text.toString(),
                    edAdditional.text.toString()
                )
                val intent = Intent()
                intent.putExtra("result", result)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            else {
                Toast.makeText(this, "Не заполнены обязательные поля", Toast.LENGTH_LONG).show()
            }
        }

        edName.addTextChangedListener { object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                edName.error = null
            }
        } }

        edPeriod.addTextChangedListener { object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                edPeriod.error = null
            }
        } }

        edMaxSpeed.addTextChangedListener { object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                edMaxSpeed.error = null
            }
        } }

        edNumOfDoors.addTextChangedListener { object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                edNumOfDoors.error = null
            }
        } }



    }

    private fun checkAttributes(): Boolean {
        var flag = true
        if (edName.text.toString() == "") {
            edName.error = "Поле должно быть заполнено"
            flag = false
        }
        if (edPeriod.text.toString() == "") {
            edPeriod.error = "Поле должно быть заполнено"
            flag = false
        }
        if (edMaxSpeed.text.toString() == "") {
            edMaxSpeed.error = "Поле должно быть заполнено"
            flag = false
        }
        if (edNumOfDoors.text.toString() == "") {
            edNumOfDoors.error = "Поле должно быть заполнено"
            flag = false
        }

        return flag
    }

}
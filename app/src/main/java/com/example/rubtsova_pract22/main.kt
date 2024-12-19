package com.example.rubtsova_pract22

import android.app.DownloadManager.Request
import android.graphics.Color
import android.health.connect.datatypes.units.Temperature
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import androidx.compose.material3.Snackbar

import com.android.volley.toolbox.StringRequest
import org.json.JSONObject

class main : AppCompatActivity() {
    private lateinit var city1:EditText
    private lateinit var cityty:TextView
    private lateinit var temperature:TextView
    private lateinit var air:TextView
    private lateinit var wind:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun result(view: View) {
        city1=findViewById(R.id.edit_text_id)
        cityty=findViewById(R.id.cityty)
        temperature=findViewById(R.id.temp)
        air=findViewById(R.id.textView8)
        wind=findViewById(R.id.textView9)

        if(city1.text.toString().isNotEmpty()&&city1.text.toString()!=null)
        {
            var key="7b1eec9b09917bbcfaf88a8dd624803b"
            var url="https://api.openweathermap.org/data/2.5/weather?q="+city1.text.toString()+"&appid="+key+"&units=metric&lang=ru"
            val queue=Volley.newRequestQueue(this)
            val stringRequest=StringRequest(
                com.android.volley.Request.Method.GET,
                url,
                {
                        response->
                    val obj=JSONObject(response)

                    val cityName=obj.getString("name")
                    cityty.text=cityName

                    val tempObj=obj.getJSONObject("main")
                    val temp=tempObj.getDouble("temp")
                    temperature.text="$temp C"
                   /* Log.d("MyLog","Response:${temp.getString("temp")}")*/
                  /*  temperature.text=temp.getString("temp").toString()*/


                    val airObj=obj.getJSONObject("main")
                    val pressure=airObj.getDouble("pressure")
                    air.text=pressure.toString()


                    val windObj=obj.getJSONObject("wind")
                    val windspeed=windObj.getDouble("speed")
                    wind.text="$windspeed m/c"

                },
                {
                    Log.d("MyLog","Volley error: $it")
                }
            )
            queue.add(stringRequest)
        }
        else
        {
            var sn= com.google.android.material.snackbar.Snackbar.make(findViewById(android.R.id.content),"Такого города нет", com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
            sn.setActionTextColor(android.graphics.Color.RED)
            sn.show()
        }

    }

    }

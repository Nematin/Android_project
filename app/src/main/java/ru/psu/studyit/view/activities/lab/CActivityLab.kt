package ru.psu.studyit.view.activities.lab

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

import ru.psu.studyit.R

import ru.psu.studyit.view.activities.CActivityBase
import kotlinx.android.synthetic.main.activity_lab.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


/********************************************************************************************************
 * Активность                                                                                           *
 * отображает данные по одной контрольной точке                                                         *
 * позволяет создать и отправить отчёт по КТ, отслеживать статус его проверки.                          *
 * @author Баландин А.Д. 2019 0201.                                                                     *
 *******************************************************************************************************/
class CActivityLab                          :
    CActivityBase()
{
    private var data: String? = null
    private var text: TextView? = null

    private var loadJson: Thread? = null

    private val tags = arrayOf(MainActivity.TAG_ID, MainActivity.CREATED_AT, MainActivity.UPDATED_AT, MainActivity.ACTIVE, MainActivity.TAG_NAME, MainActivity.DESCRIPTION)

    private var helper: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab)

        viewPagerActivityLab.adapter        = PagerAdapter(this, supportFragmentManager, 4)
        ///////////////////////////

        loadJson = Thread(Runnable {
            //Загрузка большой стринги с вашей ссылки
            getData("http://82.118.128.112:12002/subjects")
        })

        loadJson!!.start()

        formData() // формирование данных



        //////////////////////////
    }

    private suspend fun getData(path: String) {
        try {
            val url = URL(path)
            val c = url.openConnection() as HttpURLConnection
            c.requestMethod = "GET"
            c.readTimeout = 10000
            c.connect()

            val reader = BufferedReader(InputStreamReader(c.inputStream))

            val buf = StringBuilder()
            var line: String

            while ((reader.readLine()) != null) {
                buf.append(reader.readLine() + "\n")
            }

            //это полубесполезные строки, добавил для того, чтобы у меня появился JsonArray
            data = buf.toString()
            data = "{\"json\" :$data}"

            reader.close()
        } catch (e: Exception) {
            println(e.toString())
        }
    }



    fun saveLab(view: View)
    {
        //Скорее всего здесь будет код для сохранения лабораторной

        val toast = Toast.makeText(
            applicationContext,
            "ДОБААААВЬ меня", Toast.LENGTH_SHORT
        )
        toast.show()

    }


}

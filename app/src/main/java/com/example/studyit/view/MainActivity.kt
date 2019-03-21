//@author Баландин, Дегтяникова, Золотарёв, Балышев
package com.example.studyit.view

import android.content.Intent
import android.os.Bundle

import android.view.View

import java.util.ArrayList
import java.util.UUID
import java.util.concurrent.Callable

import com.example.studyit.LabTabs
import com.example.studyit.R
import com.example.studyit.data.services.IServiceLab
import com.example.studyit.model.CLab

import javax.inject.Inject

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var serviceLab: IServiceLab

    internal var tasks: ArrayList<CLab> = ArrayList()

    private var recyclerView : RecyclerView? =null

    override fun shouldUpRecreateTask(targetIntent: Intent): Boolean {
        return super.shouldUpRecreateTask(targetIntent)
    }

    fun onFABClick(view: View) {
        val intent = Intent(this, NewLab::class.java)
        startActivity(intent)
    }


    fun onFABQRClick(view: View) {
        val intent = Intent(this, qrCode::class.java)
        startActivity(intent)
    }

    fun onRecListClick(view: View) {
        val intent = Intent(this, LabTabs::class.java)
        startActivity(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Single.fromCallable {
            serviceLab!!.save(CLab("Lab1", "11", "1", null))
            serviceLab!!.save(CLab("Lab2", "12", "2", null))
            serviceLab!!.save(CLab("Lab3", "13", "3", null))
            serviceLab!!.save(CLab("Lab4", "14", "4", null))
            "123"
        }
                .flatMap {  serviceLab!!.get()}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    labs -> {
                        tasks.addAll(labs)
                    recyclerView?.adapter?.notifyDataSetChanged()
                    }
                }




        recyclerView = findViewById<View>(R.id.RecyclerViewTasks) as RecyclerView

        val llm = LinearLayoutManager(this)
        recyclerView?.layoutManager = llm

        val adapter = ViewAdaptor(this, tasks)
        // устанавливаем для списка адаптер
        recyclerView?.adapter = adapter


    }

    fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, LabTabs::class.java)
        startActivity(intent)
    }

}

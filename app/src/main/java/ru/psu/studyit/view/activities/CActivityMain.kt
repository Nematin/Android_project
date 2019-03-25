//@author Баландин, Дегтяникова, Золотарёв, Балышев
package ru.psu.studyit.view.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.View

import java.util.ArrayList

import ru.psu.studyit.R
import ru.psu.studyit.data.services.IServiceLab
import ru.psu.studyit.model.CLab

import javax.inject.Inject

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.psu.studyit.LabTabs
import ru.psu.studyit.view.adapters.CRecyclerViewAdapterLabs

class CActivityMain : CActivityBase() {

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
//
//        val test = ArrayList<CLab>()
//        test.add(CLab("Lab1", "01", "1", null))
//        test.add(CLab("Lab1", "02", "1", null))
//        test.add(CLab("Lab1", "03", "1", null))
        registerDisposable(Single.fromCallable {
            serviceLab.save(CLab("Lab1", "11", "1", null))
            serviceLab.save(CLab("Lab2", "12", "2", null))
            serviceLab.save(CLab("Lab3", "13", "3", null))
            serviceLab.save(CLab("Lab4", "14", "4", null))
            "123"
        }
                .flatMap {
                    serviceLab.get()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                        tasks.addAll(it)
                        recyclerView?.adapter?.notifyDataSetChanged()
                    },
                    {
                        Log.e("STUDYIT", "Exception while fetching lab list", it)
                    }
                )
        )


        recyclerView = findViewById<View>(R.id.RecyclerViewTasks) as RecyclerView

        val llm = LinearLayoutManager(this)
        recyclerView?.layoutManager = llm

        val adapter = CRecyclerViewAdapterLabs(this, tasks)
        // устанавливаем для списка адаптер
        recyclerView?.adapter = adapter


    }

    fun onItemClick(view: View, position: Int) {
        val intent = Intent(this, LabTabs::class.java)
        startActivity(intent)
    }

}

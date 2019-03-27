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
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.content_activity_main.*
import ru.psu.studyit.view.activities.lab.CActivityLab
import ru.psu.studyit.view.adapters.CRecyclerViewAdapterLabs

import kotlinx.android.synthetic.main.activity_main.*


class CActivityMain                         : CActivityBase() {

    @Inject
    lateinit var serviceLab                 : IServiceLab

    internal var tasks                      : ArrayList<CLab>
                                            = ArrayList()

    fun fabOpenLabClick(view: View) {
        floatingActionsMenuOpenLab.collapse()
        val intent                          = Intent(this, CActivityLab::class.java)
        startActivity(intent)
    }


    fun fabOpenLabQRClick(view: View) {
        floatingActionsMenuOpenLab.collapse()
        val intent                          = Intent(this, CActivityQRScanner::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initControls()
        updateTasks()
    }
    private fun initControls()
    {
        RecyclerViewTasks?.layoutManager    = LinearLayoutManager(this)
        // устанавливаем для списка адаптер
        RecyclerViewTasks?.adapter          = CRecyclerViewAdapterLabs(this)

        FrameLayoutInterceptor.setOnTouchListener(View.OnTouchListener { _, _ ->
            if (floatingActionsMenuOpenLab.isExpanded)
            {
                floatingActionsMenuOpenLab.collapse()
                return@OnTouchListener true
            }
            false
        })
    }
    private fun updateTasks()
    {
        //Асинхронный запрос данных по отчётам по лабораторным работам в БД.
        registerDisposable(
            //Запрашиваем список существующих лабораторных
            serviceLab.get()
                .flatMap {
                    //Если уже есть, переходим к следующей части запроса.
                    if (it.isNotEmpty())
                        Single.fromCallable { "123" }
                    //Если ни одной нет, добавляем тестовые данные.
                    else
                        Single.fromCallable {
                            serviceLab.save(CLab("Lab1", "11", "1", null))
                            serviceLab.save(CLab("Lab2", "12", "2", null))
                            serviceLab.save(CLab("Lab3", "13", "3", null))
                            serviceLab.save(CLab("Lab4", "14", "4", null))
                            "123"
                        }
                }
                //Запрашиваем актуализированный список в БД
                .flatMap {
                    serviceLab.get()
                }
                //Выполняем все действия в фоновом потоке.
                .subscribeOn(Schedulers.io())
                //Результат получаем в основном потоке.
                .observeOn(AndroidSchedulers.mainThread())
                //Обрабатываем результат - выводим данные на экран.
                .subscribe (
                    {
                        (RecyclerViewTasks?.adapter as CRecyclerViewAdapterLabs?)?.labs?.addAll(it)
                            .let {
                                RecyclerViewTasks?.adapter?.notifyDataSetChanged()
                            }

                    },
                    {
                        Log.e("STUDYIT", "Exception while fetching lab list", it)
                    }
                )
        )
    }
}

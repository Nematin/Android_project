package ru.psu.studyit.view.adapters

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


import java.io.File
import java.util.ArrayList

import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView

import ru.psu.studyit.R

/********************************************************************************************************
 * Базовый класс для всех активностей, содержит реализацию общих технических методов.                   *
 * @author Балышев А.М. 2019 0323.                                                                      *
 */
class CRecyclerViewAdapterFiles(
    private val context: Context, private val paths: ArrayList<String>
) : RecyclerView.Adapter<CRecyclerViewAdapterFiles.FileViewHolder>()
{
    private var imageSize: Int = 0

    init
    {
        setColumnNumber(context, 3)
    }

    private fun setColumnNumber(context: Context, columnNum: Int)
    {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(metrics)
        val widthPixels = metrics.widthPixels
        imageSize = widthPixels / columnNum
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder
    {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return FileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int)
    {
        val path = paths[position]
        Glide.with(context)
            .load(File(path))
            .apply(
                RequestOptions.centerCropTransform()
                    .dontAnimate()
                    .override(imageSize, imageSize)
                    .placeholder(R.drawable.image_placeholder)
            )
            .thumbnail(0.5f)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int
    {
        return paths.size
    }

    class FileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

        internal var imageView: AppCompatImageView

        init
        {
            imageView = itemView.findViewById(R.id.iv_photo)
        }
    }
}

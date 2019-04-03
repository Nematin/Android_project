package ru.psu.studyit.view.adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.io.File;
import java.util.ArrayList;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import ru.psu.studyit.R;

/********************************************************************************************************
 * Базовый класс для всех активностей, содержит реализацию общих технических методов.                   *
 * @author Балышев А.М. 2019 0323.                                                                      *
 *******************************************************************************************************/
public class CRecyclerViewAdapterFiles extends RecyclerView.Adapter<CRecyclerViewAdapterFiles.FileViewHolder> {

    private final ArrayList<String> paths;
    private final Context context;
    private int imageSize;

    public CRecyclerViewAdapterFiles(Context context, ArrayList<String> paths) {
        this.context = context;
        this.paths = paths;
        setColumnNumber(context, 3);
    }

    private void setColumnNumber(Context context, int columnNum) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        imageSize = widthPixels / columnNum;
    }

    @Override public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new FileViewHolder(itemView);
    }

    @Override public void onBindViewHolder(FileViewHolder holder, int position) {
        String path = paths.get(position);
        Glide.with(context)
                .load(new File(path))
                .apply(RequestOptions.centerCropTransform()
                        .dontAnimate()
                        .override(imageSize, imageSize)
                        .placeholder(R.drawable.image_placeholder))
                .thumbnail(0.5f)
                .into(holder.imageView);
    }

    @Override public int getItemCount() {
        return paths.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imageView;

        public FileViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_photo);
        }
    }
}
//@author Дегтяникова Дарья

package ru.psu.studyit.view.activities.lab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import java.util.ArrayList

import droidninja.filepicker.FilePickerBuilder
import droidninja.filepicker.FilePickerConst
import droidninja.filepicker.models.sort.SortingTypes
import droidninja.filepicker.utils.Orientation
import kotlinx.android.synthetic.main.fragment_lab_files.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import ru.psu.studyit.R
import ru.psu.studyit.view.adapters.CRecyclerViewAdapterFiles

class CFragmentLabFiles                        :
    Fragment(),
    EasyPermissions.PermissionCallbacks
{

    private var photoPaths                  = ArrayList<String>()
    private var docPaths                    = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )                                       : View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lab_files, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

//        //кнопку необходимо будет перенести в CFragmentLabFiles, когда они будут работать
//        fabTakePhoto.setOnClickListener { pickPhotoClicked() }
//        //кнопку необходимо будет перенести в CFragmentLabFiles, когда они будут работать
//        fabPickFile.setOnClickListener { pickDocClicked() }
    }

    @AfterPermissionGranted(RC_PHOTO_PICKER_PERM)
    fun pickPhotoClicked()
    {
        if (EasyPermissions.hasPermissions(context!!, FilePickerConst.PERMISSIONS_FILE_PICKER))
        {
            onPickPhoto()
        }
        else
        {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                this, getString(R.string.RationaleCameraPermission),
                RC_PHOTO_PICKER_PERM, FilePickerConst.PERMISSIONS_FILE_PICKER
            )
        }
    }

    @AfterPermissionGranted(RC_FILE_PICKER_PERM)
    fun pickDocClicked()
    {
        if (EasyPermissions.hasPermissions(context!!, FilePickerConst.PERMISSIONS_FILE_PICKER))
        {
            onPickDoc()
        }
        else
        {
            // Ask for one permission
            EasyPermissions.requestPermissions(
                this, getString(R.string.RationaleFilePicker),
                RC_FILE_PICKER_PERM, FilePickerConst.PERMISSIONS_FILE_PICKER
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode)
        {
            CUSTOM_REQUEST_CODE              -> if (resultCode == Activity.RESULT_OK && data != null)
            {
                photoPaths = ArrayList()
                photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA))
            }

            FilePickerConst.REQUEST_CODE_DOC -> if (resultCode == Activity.RESULT_OK && data != null)
            {
                docPaths = ArrayList()
                docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS))
            }
        }

        addThemToView(photoPaths, docPaths)
    }

    private fun addThemToView(imagePaths: ArrayList<String>?, docPaths: ArrayList<String>?)
    {
        val filePaths = ArrayList<String>()
        if (imagePaths != null) filePaths.addAll(imagePaths)

        if (docPaths != null) filePaths.addAll(docPaths)

            val layoutManager               = StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL)
            layoutManager.gapStrategy       = StaggeredGridLayoutManager
                .GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            recyclerViewFiles.layoutManager = layoutManager

            recyclerViewFiles.adapter       = CRecyclerViewAdapterFiles(context, filePaths)
            recyclerViewFiles.itemAnimator  = DefaultItemAnimator()

        Toast.makeText(context, "Num of files selected: " + filePaths.size, Toast.LENGTH_SHORT)
            .show()
    }

    fun onPickPhoto()
    {
        val maxCount = MAX_ATTACHMENT_COUNT - docPaths.size
        if (docPaths.size + photoPaths.size == MAX_ATTACHMENT_COUNT)
        {
            Toast.makeText(
                context, "Cannot select more than $MAX_ATTACHMENT_COUNT items",
                Toast.LENGTH_SHORT
            ).show()
        }
        else
        {
            FilePickerBuilder.instance
                .setMaxCount(maxCount)
                .setSelectedFiles(photoPaths)
                .setActivityTheme(R.style.FilePickerTheme)
                .setActivityTitle("Please select media")
                .enableVideoPicker(true)
                .enableCameraSupport(true)
                .showGifs(true)
                .showFolderView(true)
                .enableSelectAll(false)
                .enableImagePicker(true)
                .setCameraPlaceholder(R.drawable.custom_camera)
                .withOrientation(Orientation.UNSPECIFIED)
                .pickPhoto(this, CUSTOM_REQUEST_CODE)
        }
    }

    //
    fun onPickDoc()
    {
        val zips = arrayOf(".zip", ".rar", ".7z")
        val docs = arrayOf(".doc", ".txt")// по какой-то причине приложение вылетает
        val pdfs = arrayOf(".pdf") //по какой-то причине приложение вылетает
        val maxCount = MAX_ATTACHMENT_COUNT - photoPaths.size
        if (docPaths.size + photoPaths.size == MAX_ATTACHMENT_COUNT)
        {
            Toast.makeText(
                context, "Cannot select more than $MAX_ATTACHMENT_COUNT items",
                Toast.LENGTH_SHORT
            ).show()
        }
        else
        {
            FilePickerBuilder.instance
                .setMaxCount(maxCount)
                .setSelectedFiles(docPaths)
                .setActivityTheme(R.style.FilePickerTheme)
                .setActivityTitle("Please select doc")
                .addFileSupport("ZIP", zips)
                .addFileSupport("DOCS", docs) //по какой-то причине приложение вылетает
                .addFileSupport("PDF", pdfs, R.drawable.pdf_blue) //по какой-то причине приложение вылетает
                .enableDocSupport(false)
                .enableSelectAll(true)
                .sortDocumentsBy(SortingTypes.name)
                .withOrientation(Orientation.UNSPECIFIED)
                .pickFile(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    )
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>)
    {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>)
    {

        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms))
        {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    companion object
    {
        private const val MAX_ATTACHMENT_COUNT  = 10
        const val RC_PHOTO_PICKER_PERM          = 123
        const val RC_FILE_PICKER_PERM           = 321
        private const val CUSTOM_REQUEST_CODE   = 532
    }
}// Required empty public constructor

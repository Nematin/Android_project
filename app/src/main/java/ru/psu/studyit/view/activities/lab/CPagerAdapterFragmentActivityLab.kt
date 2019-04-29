//@author Дегтяникова Дарья
package ru.psu.studyit.view.activities.lab

import android.content.Context

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import ru.psu.studyit.R

class CPagerAdapterFragmentActivityLab(
    internal var context                    : Context,
    fm                                      : FragmentManager
)                                           : FragmentPagerAdapter(fm)
{

    override fun getItem(
        position                            : Int
    )                                       : Fragment
    {
        return when (position)
        {
            0                               -> CFragmentLabDetails()
            1                               -> Fragment_materials()
            2                               -> CFragmentLabFiles()
            3                               -> Fragment_notes()
            else                            -> CFragmentLabDetails()
        }
    }

    override fun getPageTitle(
        position                            : Int
    )                                       : CharSequence?
    {
        return when (position)
        {
            0                               -> context.getString(R.string.Details)
            1                               -> context.getString(R.string.Materials)
            2                               -> context.getString(R.string.Files)
            3                               -> context.getString(R.string.Comments)
            else                            -> ""
        }
    }

    override fun getCount()                 = 4
}

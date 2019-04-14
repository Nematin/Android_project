//@author Дегтяникова Дарья
package ru.psu.studyit.view.activities.lab

import android.content.Context

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import ru.psu.studyit.R

class PagerAdapter(
    internal var context: Context,
    fm: FragmentManager,
    internal var mNumOfTabs: Int
) : FragmentStatePagerAdapter(fm)
{

    override fun getItem(position: Int): Fragment
    {

        when (position)
        {
            0    ->
            {
                return CFragmentLabDetails()
            }

            1    ->
            {
                return Fragment_materials()
            }
            2    ->
            {
                return CFragmentLabFiles()
            }
            3    ->
            {
                return Fragment_notes()
            }
            else -> return CFragmentLabDetails()
        }
    }

    override fun getPageTitle(position: Int): CharSequence?
    {
        //return "Page " + position;

        when (position)
        {
            0    -> return context.getString(R.string.Details)
            1    -> return context.getString(R.string.Materials)
            2    -> return context.getString(R.string.Files)
            3    -> return context.getString(R.string.Comments)
            else -> return null
        }
    }

    override fun getCount(): Int
    {
        return mNumOfTabs
    }

}

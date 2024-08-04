package com.hiskytechs.translate360.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hiskytechs.translate360.Fragments.AuthorFragment
import com.hiskytechs.translate360.Fragments.FragmentQuote

class AdapterFragmentPages (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> FragmentQuote()

            else-> AuthorFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2 // Number of tabs
    }
}

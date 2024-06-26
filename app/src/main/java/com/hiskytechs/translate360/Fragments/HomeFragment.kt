package com.hiskytechs.translate360.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.hiskytechs.translate360.R
import com.hiskytechs.translate360.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.tvText.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_textTranslateFragment)
        }
        binding.tvQuotes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragmentQuote)
        }
        binding.navigationView.setNavigationItemSelectedListener(this)

        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}
            override fun onDrawerOpened(drawerView: View) {}
            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerStateChanged(newState: Int) {}
        })

        binding.openDrawer.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        return binding.root
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bookmarks -> {
                findNavController().navigate(R.id.action_homeFragment_to_fragmentBookMarks)
            }
            R.id.history -> {
                findNavController().navigate(R.id.action_homeFragment_to_fragmentHistory)
            }
            R.id.subscription -> {
                findNavController().navigate(R.id.action_homeFragment_to_fragmentSubscription)
            }
            R.id.updates -> {
                findNavController().navigate(R.id.action_homeFragment_to_fragmentsCheckUpdates)
            }
            R.id.privacy -> {
                findNavController().navigate(R.id.action_homeFragment_to_fragmentPrivacyPolicy)
            }
            R.id.language -> {
                findNavController().navigate(R.id.action_homeFragment_to_fragmentLanguage)
            }

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

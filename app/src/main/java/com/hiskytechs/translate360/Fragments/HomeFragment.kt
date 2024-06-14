package com.hiskytechs.translate360.Fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.hiskytechs.translate360.R
import com.hiskytechs.translate360.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        drawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment), drawerLayout
        )
        binding.openDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.tvText.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_textTranslateFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navView: NavigationView = binding.navigationView
        navView.setNavigationItemSelectedListener(this)
        NavigationUI.setupWithNavController(navView, findNavController())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bookmarks -> {
                findNavController().navigate(R.id.action_homeFragment_to_bookMarkFragment)
                Toast.makeText(requireActivity(), "dfdfssd", Toast.LENGTH_SHORT).show()
            }

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(findNavController(), appBarConfiguration)
    }
}

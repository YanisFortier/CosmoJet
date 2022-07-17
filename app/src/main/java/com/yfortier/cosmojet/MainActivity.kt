package com.yfortier.cosmojet

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.yfortier.cosmojet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		WindowCompat.setDecorFitsSystemWindows(window, false)
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

	}

	fun estCategorieValide(chipGroup:ChipGroup): Boolean {
		var categorieValide = false
		if (findViewById<Chip>(chipGroup.checkedChipId) != null) {
			categorieValide = findViewById<Chip>(chipGroup.checkedChipId).text.toString().isNotEmpty()
		}
		return categorieValide
	}

}
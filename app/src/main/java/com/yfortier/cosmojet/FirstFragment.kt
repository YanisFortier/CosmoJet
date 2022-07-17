package com.yfortier.cosmojet

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.yfortier.cosmojet.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
	private var _binding: FragmentFirstBinding? = null
	private val binding get() = _binding!!

	private lateinit var chipGroupParticipants: ChipGroup
	private lateinit var chipGroupCategories: ChipGroup
	private lateinit var textInputParticipant: TextInputEditText

	private val listeParticipants: ArrayList<String> = ArrayList()

	override fun onCreateView(
			inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
	): View {
		_binding = FragmentFirstBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		textInputParticipant = binding.textInputParticipant
		chipGroupParticipants = binding.chipGroupParticipants
		chipGroupCategories = binding.chipGroupCategories

		activerAjoutParticipant()
		activerChoixCategories()
	}

	private fun activerChoixCategories() {
		chipGroupCategories.setOnCheckedChangeListener { _, _ ->
			verifierSiEvenementValide()
			binding.layoutParticipants.visibility = VISIBLE
		}
	}

	private fun activerAjoutParticipant() {
		binding.boutonAjouterParticipant.setOnClickListener {
			chipGroupParticipants.removeView(binding.chipOriginal)
			val prenomParticipant = textInputParticipant.text.toString().trim()
			if (prenomParticipant.isNotEmpty()) {
				val chip = Chip(this.requireContext())
				val emojiRandom = this.requireContext().resources.getStringArray(R.array.emojis).random()
				chip.text = getString(R.string.ajout_participant, emojiRandom, prenomParticipant)
				chip.isCloseIconVisible = true

				activerSuppressionParticipants(chip)
				textInputParticipant.setText("")
				textInputParticipant.hint = "Participant"

				chipGroupParticipants.addView(chip)
				listeParticipants.add(prenomParticipant)
				verifierSiEvenementValide()
			}
		}
	}

	private fun activerSuppressionParticipants(chip: Chip) {
		chip.setOnCloseIconClickListener {
			var index = 0
			for (children in chipGroupParticipants.children) {
				if (children.id == chip.id) {
					listeParticipants.removeAt(index)
					chipGroupParticipants.removeView(it)
					verifierSiEvenementValide()
				}
				index++
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun verifierSiEvenementValide() {
		val categorieValide = (activity as MainActivity).estCategorieValide(chipGroupCategories)
		val listeParticipantValide = listeParticipants.isNotEmpty()
		val estEvenementValide = categorieValide && listeParticipantValide
		binding.boutonDemarrer.isVisible = estEvenementValide
		if (listeParticipants.size >= 15) {
			binding.layoutAjouterParticipant.visibility = GONE
			hideKeyboard()
		} else {
			binding.layoutAjouterParticipant.visibility = VISIBLE
		}
	}

	private fun Fragment.hideKeyboard() {
		view?.let { activity?.hideKeyboard(it) }
	}

	private fun Context.hideKeyboard(view: View) {
		val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
		inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
	}
}
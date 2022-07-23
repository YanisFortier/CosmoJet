package com.yfortier.cosmojet

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.yfortier.cosmojet.databinding.FragmentSecondBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.model.CarouselType

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SelectionJeux : Fragment() {

	private var _binding: FragmentSecondBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
			inflater: LayoutInflater, container: ViewGroup?,
			savedInstanceState: Bundle?
	): View {

		_binding = FragmentSecondBinding.inflate(inflater, container, false)
		return binding.root

	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.boutonDemarrer.setOnClickListener {
			findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
		}


		initialiserCarousel()
	}

	private fun initialiserCarousel() {
		val carousel: ImageCarousel = binding.carousel
		carousel.carouselType = CarouselType.SHOWCASE
		carousel.registerLifecycle(lifecycle)

		val listeImages = mutableListOf<CarouselItem>()
		listeImages.add(CarouselItem(imageDrawable = R.drawable.palmier_backgroud, caption = "🌴 Palmier"))
		listeImages.add(CarouselItem(imageDrawable = R.drawable.cosmojet_background, caption = "🚀 CosmoJet"))
		listeImages.add(CarouselItem(imageDrawable = R.drawable.mytho_background, caption = "🤫 Mytho !"))

		carousel.setData(listeImages)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}
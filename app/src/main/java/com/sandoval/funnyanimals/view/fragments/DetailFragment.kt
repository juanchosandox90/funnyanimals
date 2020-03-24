package com.sandoval.funnyanimals.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sandoval.funnyanimals.R
import com.sandoval.funnyanimals.model.Animal
import com.sandoval.funnyanimals.util.getProgressDrawable
import com.sandoval.funnyanimals.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_animal.*
import kotlinx.android.synthetic.main.item_animal.animalNameDetail

class DetailFragment : Fragment() {

    private var animalArg: Animal? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            animalArg = DetailFragmentArgs.fromBundle(it).animalArg
        }
        context?.let {
            animalImageDetail.loadImage(animalArg?.imageUrl, getProgressDrawable(it))
        }
        animalNameDetail.text = animalArg?.name
        animalLocationDetail.text = animalArg?.location
        animalLifespanDetail.text = animalArg?.lifeSpan
        animalDietDetail.text = animalArg?.diet
    }
}

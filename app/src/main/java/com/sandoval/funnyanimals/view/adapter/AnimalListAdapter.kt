package com.sandoval.funnyanimals.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sandoval.funnyanimals.R
import com.sandoval.funnyanimals.model.Animal
import com.sandoval.funnyanimals.util.getProgressDrawable
import com.sandoval.funnyanimals.util.loadImage
import com.sandoval.funnyanimals.view.fragments.ListFragmentDirections
import kotlinx.android.synthetic.main.item_animal.view.*

class AnimalListAdapter(private val animalList: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    fun updateAnimalList(newAnimalList: List<Animal>) {
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun getItemCount(): Int = animalList.count()

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.view.animalNameDetail.text = animalList[position].name
        holder.view.animalImage.loadImage(
            animalList[position].imageUrl,
            getProgressDrawable(holder.view.context)
        )
        holder.view.animalLayout.setOnClickListener {
            val action = ListFragmentDirections.actionDetail(animalList[position])
            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    class AnimalViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}
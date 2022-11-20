package com.ssau.sunsystem.ui.screen.planets

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.ssau.sunsystem.R

class PlanetsAdapter(
    planets: List<CustomizedPlanet>,
    private val onSaveState: (planets: List<CustomizedPlanet>) -> Unit,
) : RecyclerView.Adapter<PlanetsAdapter.ViewHolder>() {

    private val planets: MutableList<CustomizedPlanet> = mutableListOf()
    private var onColorPickClick: ((position: Int) -> Unit)? = null

    init {
        this.planets.addAll(planets)
    }

    fun setOnColorPickClick(onColorPickClick: (position: Int) -> Unit) {
        this.onColorPickClick = onColorPickClick
    }

    fun addPlanet(planet: CustomizedPlanet) {
        planets.add(planet)
        onSaveState.invoke(planets)
        notifyItemInserted(planets.size - 1)
    }

    fun deletePlanet(position: Int) {
        planets.removeAt(position)
        onSaveState.invoke(planets)
        notifyItemRemoved(position)
    }

    fun getPlanets(): List<CustomizedPlanet> {
        return planets
    }

    fun setPlanetColor(position: Int, color: Int) {
        planets[position].color = color
        onSaveState.invoke(planets)
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_planet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = planets.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameListener = EditTextListener {
            planets[adapterPosition].name = it
            onSaveState.invoke(planets)
        }

        private val massListener = EditTextListener {
            planets[adapterPosition].mass = it.toDoubleOrNull() ?: 0.0
            onSaveState.invoke(planets)
        }

        private val coordinateXListener = EditTextListener {
            planets[adapterPosition].x = it.toDoubleOrNull() ?: 0.0
            onSaveState.invoke(planets)
        }

        private val coordinateYListener = EditTextListener {
            planets[adapterPosition].y = it.toDoubleOrNull() ?: 0.0
            onSaveState.invoke(planets)
        }

        private val coordinateZListener = EditTextListener {
            planets[adapterPosition].z = it.toDoubleOrNull() ?: 0.0
            onSaveState.invoke(planets)
        }

        private val velocityXListener = EditTextListener {
            planets[adapterPosition].velocityX = it.toDoubleOrNull() ?: 0.0
            onSaveState.invoke(planets)
        }

        private val velocityYListener = EditTextListener {
            planets[adapterPosition].velocityY = it.toDoubleOrNull() ?: 0.0
            onSaveState.invoke(planets)
        }

        private val velocityZListener = EditTextListener {
            planets[adapterPosition].velocityZ = it.toDoubleOrNull() ?: 0.0
            onSaveState.invoke(planets)
        }

        fun bind(position: Int) {
            nameListener.updatePosition(position)
            itemView.findViewById<TextInputEditText>(R.id.et_planet_name)
                .let {
                    it.setText(planets[position].name)
                    it.addTextChangedListener(nameListener)
                }

            massListener.updatePosition(position)
            itemView.findViewById<TextInputEditText>(R.id.et_mass)
                .let {
                    it.setText(planets[position].mass.toString())
                    it.addTextChangedListener(massListener)
                }

            coordinateXListener.updatePosition(position)
            itemView.findViewById<TextInputEditText>(R.id.et_c_x)
                .let {
                    it.setText(planets[position].x.toString())
                    it.addTextChangedListener(coordinateXListener)
                }

            coordinateYListener.updatePosition(position)
            itemView.findViewById<TextInputEditText>(R.id.et_c_y)
                .let {
                    it.setText(planets[position].y.toString())
                    it.addTextChangedListener(coordinateYListener)
                }

            coordinateZListener.updatePosition(position)
            itemView.findViewById<TextInputEditText>(R.id.et_c_z)
                .let {
                    it.setText(planets[position].z.toString())
                    it.addTextChangedListener(coordinateZListener)
                }

            velocityXListener.updatePosition(position)
            itemView.findViewById<TextInputEditText>(R.id.et_v_x)
                .let {
                    it.setText(planets[position].velocityX.toString())
                    it.addTextChangedListener(velocityXListener)
                }

            velocityYListener.updatePosition(position)
            itemView.findViewById<TextInputEditText>(R.id.et_v_y)
                .let {
                    it.setText(planets[position].velocityY.toString())
                    it.addTextChangedListener(velocityYListener)
                }

            velocityZListener.updatePosition(position)
            itemView.findViewById<TextInputEditText>(R.id.et_v_z)
                .let {
                    it.setText(planets[position].velocityZ.toString())
                    it.addTextChangedListener(velocityZListener)
                }

            itemView.findViewById<ImageView>(R.id.color_picker).let {
                it.setOnClickListener {
                    onColorPickClick?.invoke(position)
                }
                it.setColorFilter(planets[position].color)
            }
        }

        inner class EditTextListener(
            private val update: (text: String) -> Unit = {  },
        ) : TextWatcher {
            private var listPosition = 0

            fun updatePosition(position: Int) {
                listPosition = position
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(string: CharSequence, start: Int, before: Int, count: Int) {
                update.invoke(string.toString())
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }
}

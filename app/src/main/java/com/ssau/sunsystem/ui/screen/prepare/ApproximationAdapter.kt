package com.ssau.sunsystem.ui.screen.prepare

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.ssau.sunsystem.R
import com.ssau.sunsystem.ui.model.ApproximationMethod

class ApproximationAdapter(context: Context) : RecyclerView.Adapter<ApproximationAdapter.ApproximationViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApproximationViewHolder {
        return ApproximationViewHolder(inflater.inflate(R.layout.approximation_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ApproximationViewHolder, position: Int) {
        holder.bind(ApproximationMethod.values()[position])
    }

    override fun getItemCount(): Int = ApproximationMethod.values().size

    class ApproximationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(method: ApproximationMethod) {
            itemView.findViewById<TextView>(R.id.method_title).text =
                itemView.context.resources.getStringArray(R.array.methods)[method.ordinal]
            itemView.findViewById<TextView>(R.id.method_info).text =
                itemView.context.resources.getStringArray(R.array.methods_info)[method.ordinal]
            itemView.findViewById<ImageView>(R.id.method_image).setImageResource(toImageRes(method))
        }

        @DrawableRes
        private fun toImageRes(method: ApproximationMethod): Int =
            when(method) {
                ApproximationMethod.EULER -> R.drawable.euler
                ApproximationMethod.EULER_CRAMER -> R.drawable.euler_cramer
                ApproximationMethod.BEEMAN -> R.drawable.beeman
                ApproximationMethod.VERLET -> R.drawable.verlet
            }
    }
}

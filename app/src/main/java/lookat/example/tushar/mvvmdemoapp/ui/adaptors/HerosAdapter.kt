package lookat.example.tushar.allstatemobile.ui.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lookat.example.tushar.allstatemobile.model.HerosModel
import lookat.example.tushar.mvvmdemoapp.R


class HerosAdapter(val heroList: List<HerosModel>?) : RecyclerView.Adapter<HerosAdapter.HeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        return HeroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent, false))
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {

        holder.txtName.setText(heroList?.get(position)?.name)
    }

    override fun getItemCount(): Int {
        return heroList?.size!!
    }

    inner class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtName: TextView = itemView.findViewById(R.id.txtNameId)

    }
}
package com.example.quizzapppro.bd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.quizzapppro.R

class customScoreAdapter(private val context : Context, private var scoresArrayList: ArrayList<Puntaje>) : BaseAdapter() {

    private val myImageList = intArrayOf(R.drawable.ginjirotchi, R.drawable.hashizotchi, R.drawable.kuchipatchi, R.drawable.mametchi, R.drawable.mimitchi, R.drawable.pochitchi)

    val db = AppDatabase.getAppDatabase(context)
    lateinit var perfil: Perfil

    override fun getItem(position: Int): Any {
        return scoresArrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return scoresArrayList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val holder: ScoreViewHolder

        perfil = db.perfilDao().getPerfilById(scoresArrayList[position].perfil_idJugador)

        if (convertView == null) {
            holder = ScoreViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.score_adapter, null, true)

            holder.tvNick = convertView!!.findViewById(R.id.score_nick) as TextView
            holder.tvScore = convertView!!.findViewById(R.id.score) as TextView
            holder.pIcon = convertView!!.findViewById(R.id.score_icon) as ImageView
            holder.iconTrampa = convertView!!.findViewById(R.id.score_trampa) as ImageView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ScoreViewHolder
        }

        holder.tvNick!!.setText(perfil.nombreJugador)
        holder.tvScore!!.setText(scoresArrayList[position].puntaje.toString())
        holder.pIcon!!.setImageResource(myImageList[perfil.avatar])

        if(scoresArrayList[position].cheated == 1){
            holder.iconTrampa!!.setImageResource(R.drawable.tarakotchi_original)
        }
        else{
            holder.iconTrampa!!.visibility = View.GONE
        }

        return convertView
    }

    private inner class ScoreViewHolder {

        var tvNick: TextView? = null
        var tvScore: TextView? = null
        internal var pIcon: ImageView? = null
        internal var iconTrampa: ImageView? = null

    }
}
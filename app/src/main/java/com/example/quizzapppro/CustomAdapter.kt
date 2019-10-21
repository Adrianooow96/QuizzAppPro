package com.example.quizzapppro

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.example.quizzapppro.bd.AppDatabase
import com.example.quizzapppro.bd.Perfil

class CustomAdapter(private val context : Context, private var profilesArrayList: ArrayList<Perfil>) : BaseAdapter() {
    val db = AppDatabase.getAppDatabase(context)
    val perfil: Perfil = db.perfilDao().getCurrentPerfil()

    private val myImageList = intArrayOf(R.drawable.ginjirotchi, R.drawable.hashizotchi, R.drawable.kuchipatchi, R.drawable.mametchi, R.drawable.mimitchi, R.drawable.pochitchi)

    override fun getViewTypeCount(): Int {
        return count
    }

    override fun getItemViewType(position: Int): Int {

        return position
    }

    override fun getCount(): Int {
        return profilesArrayList.size
    }

    override fun getItem(position: Int): Any {
        return profilesArrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            holder = ViewHolder()
            val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.listelement, null, true)

            holder.tvNick = convertView!!.findViewById(R.id.nick) as TextView
            holder.btnEdit = convertView!!.findViewById(R.id.edit) as ImageButton
            holder.btnDelete = convertView!!.findViewById(R.id.delete) as ImageButton
            holder.btnSelect = convertView!!.findViewById(R.id.select) as ImageButton
            holder.pIcon = convertView!!.findViewById(R.id.icon) as ImageView

            convertView.tag = holder
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = convertView.tag as ViewHolder
        }

        holder.tvNick!!.setText(profilesArrayList[position].nombreJugador)
        holder.pIcon!!.setImageResource(myImageList[profilesArrayList[position].avatar])

        holder.btnDelete!!.setOnClickListener {
            val countPerfles : Int = db.perfilDao().countPerfiles()
            if(countPerfles > 1) {
                db.perfilDao().deletePerfil(db.perfilDao().getPerfilById(profilesArrayList[position].idJugador))
                Toast.makeText(
                    context,
                    "Perfil eliminado.",
                    Toast.LENGTH_SHORT
                ).show()
                profilesArrayList =
                    profilesArrayList.minus(profilesArrayList[position]) as ArrayList<Perfil>
                notifyDataSetChanged()
            }
            else
            {
                Toast.makeText(
                    context,
                    "No puedes eliminar tu único perfil.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        holder.btnEdit!!.setOnClickListener {
            val intent = Intent(context,EditProfileActivity::class.java)
            var selectedPerfil = db.perfilDao().getPerfilById(profilesArrayList[position].idJugador)
            db.perfilDao().resetStatus()
            selectedPerfil.status = 1
            db.perfilDao().updatePerfil(selectedPerfil)
            startActivity(context, intent, null)
        }

        holder.btnSelect!!.setOnClickListener {
            var selectedPerfil = db.perfilDao().getPerfilById(profilesArrayList[position].idJugador)
            if(selectedPerfil.status == 1){
                Toast.makeText(
                    context,
                    "Este perfil ya está seleccionado.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                db.perfilDao().resetStatus()
                selectedPerfil.status = 1
                db.perfilDao().updatePerfil(selectedPerfil)
                notifyDataSetChanged()
            }
        }

        return convertView
    }

    private inner class ViewHolder {

        var tvNick: TextView? = null
        var btnEdit: ImageButton? = null
        var btnDelete: ImageButton? = null
        var btnSelect: ImageButton? = null
        internal var pIcon: ImageView? = null

    }

}
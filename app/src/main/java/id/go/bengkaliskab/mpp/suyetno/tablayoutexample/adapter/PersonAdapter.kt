package id.go.bengkaliskab.mpp.suyetno.tablayoutexample.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import id.go.bengkaliskab.mpp.suyetno.tablayoutexample.databinding.ItemPersonBinding
import id.go.bengkaliskab.mpp.suyetno.tablayoutexample.model.Person

class PersonAdapter(
    private val listPerson: List<Person>
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPersonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = listPerson[position]

        // Binding Data Teks
        holder.binding.apply {
            tvName.text = "${person.firstname} ${person.lastname}"
            tvEmail.text = person.email
            tvPhone.text = person.phone
            tvGender.text = person.gender
        }

        // Binding Data Gambar dengan Glide
        Glide.with(holder.itemView.context)
            .load(person.image)
            .apply(RequestOptions.circleCropTransform())
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Simpan cache agar loading lebih cepat
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image) // Ikon berbeda jika error
            .into(holder.binding.imgPhoto)

        // Klik item untuk tampil detail alamat
        holder.itemView.setOnClickListener {
            val address = person.address
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Detail Lokasi")
                .setMessage(
                    """
                    Jalan   : ${address.street}
                    Kota    : ${address.city}
                    Negara  : ${address.country}
                    Kodepos : ${address.zipcode}
                    """.trimIndent()
                )
                .setPositiveButton("Selesai", null)
                .show()
        }
    }

    override fun getItemCount(): Int = listPerson.size
}
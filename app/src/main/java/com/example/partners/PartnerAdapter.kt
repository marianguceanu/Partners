package com.example.partners

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PartnerAdapter(private val partners: PartnerRepo) :
    RecyclerView.Adapter<PartnerAdapter.PartnerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PartnerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.partner_item, parent, false)
        return PartnerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartnerViewHolder, position: Int) {
        val partner = partners.getById(position)
        partner?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return partners.getAll().size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showUpdatePartner(parent: ViewGroup) {
        val dialogView =
            LayoutInflater.from(parent.context).inflate(R.layout.add_partner_form, null)
        val alertDialog = AlertDialog.Builder(parent.context)
            .setTitle("Add partner")
            .setView(dialogView)
            .setPositiveButton("Update") { _, _ ->
                val id: Int =
                    dialogView.findViewById<EditText>(R.id.idEditText).text.toString().toInt()
                val name: String =
                    dialogView.findViewById<EditText>(R.id.nameEditText).text.toString()
                val email: String =
                    dialogView.findViewById<EditText>(R.id.emailEditText).text.toString()
                val address: String =
                    dialogView.findViewById<EditText>(R.id.addressEditText).text.toString()
                val phoneNumber: String =
                    dialogView.findViewById<EditText>(R.id.phoneNumberEditText).text.toString()
                val ownerPhoneNumber: String =
                    dialogView.findViewById<EditText>(R.id.ownerPhoneNumberEditText).text.toString()

                val newPartner = Partner(id, name, email, address, phoneNumber, ownerPhoneNumber)
                partners.update(newPartner)
                notifyDataSetChanged()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alertDialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun showDeleteDialog(parent: ViewGroup) {
        val dialogView =
            LayoutInflater.from(parent.context).inflate(R.layout.add_partner_form, null)
        val alertDialog = AlertDialog.Builder(parent.context)
            .setTitle("Delete partner")
            .setView(dialogView)
            .setPositiveButton("Delete") { _, _ ->
                val id: Int =
                    dialogView.findViewById<EditText>(R.id.idEditText).text.toString().toInt()
                partners.delete(id)

                notifyDataSetChanged()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
    }

    inner class PartnerViewHolder(partnerView: View) :
        RecyclerView.ViewHolder(partnerView) {
        private val pv = partnerView

        @SuppressLint("SetTextI18n")
        fun bind(partner: Partner) {
            pv.findViewById<TextView>(R.id.titleTextView).text =
                "${partner.name} - ${partner.email}"
            pv.findViewById<TextView>(R.id.descriptionTextView).text =
                "Address: ${partner.address}\n" +
                        "Phone number: ${partner.phoneNumber}\n" +
                        "Owner phone number: ${partner.ownerPhoneNumber}"
            pv.findViewById<TextView>(R.id.idTextView).text = "${partner.id}"
            pv.findViewById<Button>(R.id.updateButton).setOnClickListener {
                showUpdatePartner(pv.parent as ViewGroup)
            }
            pv.findViewById<Button>(R.id.deleteButton).setOnClickListener {
                showDeleteDialog(pv.parent as ViewGroup)
            }
        }
    }
}


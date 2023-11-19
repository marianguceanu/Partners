package com.example.partners

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.partners.databinding.ActivityMainBinding
import com.example.partners.ui.theme.PartnersTheme

class MainActivity : ComponentActivity() {
    private val partnerRepo = PartnerRepo()
    private lateinit var partnerAdapter: PartnerAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        partnerAdapter = PartnerAdapter(partnerRepo)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = partnerAdapter
        binding.addButton.setOnClickListener {
            showAddPartner()
        }
//        binding.recyclerView.findViewById<View>(R.id.updateButton).setOnClickListener {
//            showUpdateDialog()
//        }
//        binding.recyclerView.findViewById<View>(R.id.deleteButton).setOnClickListener {
//            showDeleteDialog()
//        }
    }

    //    @SuppressLint("InflateParams")
    @SuppressLint("NotifyDataSetChanged")
    fun showAddPartner() {
        val dialogView = layoutInflater.inflate(R.layout.add_partner_form, null)
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Add partner")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
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

                val newPartner = Partner(0, name, email, address, phoneNumber, ownerPhoneNumber)
                partnerRepo.add(newPartner)

                partnerAdapter.notifyDataSetChanged()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        alertDialog.show()
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PartnersTheme {
        Greeting("Android")
    }
}
package com.example.customlistview

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class StoreActivity : AppCompatActivity() {

    private lateinit var editProductName: EditText
    private lateinit var editProductPrice: EditText
    private lateinit var btnUpLoadImage: Button
    private lateinit var listViewProducts: ListView
    private lateinit var btnAddProduct: Button

    private var selectedImageUri: Uri? = null
    private val products = mutableListOf<Product>()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_exit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.exit -> {
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_store)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        editProductName = findViewById(R.id.editProductName)
        editProductPrice = findViewById(R.id.editProductPrice)
        btnUpLoadImage = findViewById(R.id.btnUpLoadImage)
        listViewProducts = findViewById(R.id.listViewProducts)
        btnAddProduct = findViewById(R.id.btnAddProduct)

        productAdapter = ProductAdapter(this, products)
        listViewProducts.adapter = productAdapter

        btnUpLoadImage.setOnClickListener{
            openGallery()
        }
        btnAddProduct.setOnClickListener{
            addProduct()
        }
    }
    private fun openGallery(){
        val inten  = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        startActivityForResult(inten,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            selectedImageUri = data?.data
            Toast.makeText(this,"Фото загружено", Toast.LENGTH_LONG).show()
        }
    }

    private fun addProduct(){
        val name = editProductName.text.toString()
        val price = editProductPrice.text.toString().toDoubleOrNull()
        val imageUri = selectedImageUri

        if (name.isNotBlank() && price != null && imageUri != null){
            val product = Product(name, price, imageUri)
            products.add(product)
            productAdapter.notifyDataSetChanged()

            editProductName.text.clear()
            editProductPrice.text.clear()
            selectedImageUri = null

            Toast.makeText(this,"Продукт добавлен", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show()
        }
    }
}
package com.tai.viewp2_ex_utc2.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tai.viewp2_ex_utc2.databinding.ActivityProductBinding;
import com.tai.viewp2_ex_utc2.model.Product;

public class ProductActivity extends AppCompatActivity {

    private ActivityProductBinding binding;

    ArrayAdapter<Product> adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initAdapter();

        addEvents();
    }

    private void addEvents() {
        binding.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.edtProductName.getText().toString();
                double price = Double.parseDouble(binding.edtProductPrice.getText().toString());
                Product product = new Product(name, price);
                adapterProduct.add(product);
            }
        });

        binding.lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = adapterProduct.getItem(position);

                Toast.makeText(ProductActivity.this, selectedProduct.getNameProduct()
                        + " - " + selectedProduct.getPriceProduct(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.lvProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = adapterProduct.getItem(position);
                adapterProduct.remove(selectedProduct);
                return true;
            }
        });
    }

    private void initAdapter() {
        adapterProduct = new ArrayAdapter<>(ProductActivity.this, android.R.layout.simple_list_item_1);
        binding.lvProduct.setAdapter(adapterProduct);
    }
}

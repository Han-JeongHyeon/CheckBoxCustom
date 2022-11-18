package com.example.checkbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CompoundButton
import com.example.checkbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.checkBox.setOnCheckedChangeListener(object: CheckBoxCustomView.OnCheckedChangeListener{
            override fun onCheckChange(compoundButton: CompoundButton, isChecked: Boolean) {
                Log.d("TAG", "$isChecked")
            }
        })

    }
}
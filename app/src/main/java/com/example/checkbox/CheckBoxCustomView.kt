package com.example.checkbox

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CompoundButton
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.checkbox.databinding.CustomviewCheckboxBinding

class CheckBoxCustomView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs)  {
    private var binding: CustomviewCheckboxBinding

    private var unChecked: Drawable? = null
    private var checked: Drawable? = null

    interface OnCheckedChangeListener {
        fun onCheckChange(compoundButton: CompoundButton, isChecked: Boolean)
    }

    private var listener: OnCheckedChangeListener? = null

    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener) {
        this.listener = listener
    }

    private var checkBocWidth: Float
    private var checkBocHeight: Float

    private var textWidth: Float
    private var textHeight: Float
    private var text: String?
    private var fontSize: Float
    private var textColor: Int
    private var textFont: Typeface? = null

    init {
        binding = CustomviewCheckboxBinding.inflate(LayoutInflater.from(context), this, true)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckBoxCustomView)

        checkBocWidth = typedArray.getDimension(R.styleable.CheckBoxCustomView_c_width, 20F)
        checkBocHeight = typedArray.getDimension(R.styleable.CheckBoxCustomView_c_height, 20F)

        unChecked = ContextCompat.getDrawable(
            context,
            typedArray.getResourceId(
                R.styleable.CheckBoxCustomView_c_uncheckedBackground,
                R.drawable.unchecked
            )
        )

        checked = ContextCompat.getDrawable(
            context,
            typedArray.getResourceId(
                R.styleable.CheckBoxCustomView_c_checkedBackground,
                R.drawable.checked
            )
        )

        binding.checkBox.background = unChecked

        binding.checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            compoundButton.setBackgroundResource(if (isChecked) R.drawable.checked else R.drawable.unchecked)
            listener?.onCheckChange(compoundButton, isChecked)
        }

        textWidth = typedArray.getDimension(R.styleable.CheckBoxCustomView_t_width, 20F)
        textHeight = typedArray.getDimension(R.styleable.CheckBoxCustomView_t_height, 20F)

        text = typedArray.getString(R.styleable.CheckBoxCustomView_t_text)
        fontSize = typedArray.getFloat(R.styleable.CheckBoxCustomView_t_fontSize,16F)
        textColor = typedArray.getColor(R.styleable.CheckBoxCustomView_t_color, Color.BLACK)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textFont = typedArray.getFont(R.styleable.CheckBoxCustomView_t_font)
        }

        val textView = binding.textView

        textView.text = text
        textView.width = textWidth.toInt()
        textView.height = textHeight.toInt()
        textView.textSize = fontSize
        textView.setTextColor(textColor)
        textView.typeface = textFont
    }

    fun isChecked(): Boolean{
        return binding.checkBox.isChecked
    }

    fun setting(){
        val layoutParams = binding.checkBox.layoutParams

        layoutParams.width = checkBocWidth.toInt()
        layoutParams.height = checkBocHeight.toInt()
        binding.checkBox.layoutParams = layoutParams
    }

}
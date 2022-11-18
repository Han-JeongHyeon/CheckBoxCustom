package com.example.checkbox

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.CompoundButton
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.checkbox.databinding.CustomviewCheckboxBinding

class CheckBoxCustomView(context: Context, attrs: AttributeSet): ConstraintLayout(context, attrs)  {
    private var binding: CustomviewCheckboxBinding

    interface OnCheckedChangeListener {
        fun onCheckChange(compoundButton: CompoundButton, isChecked: Boolean)
    }

    private var listener: OnCheckedChangeListener? = null

    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener) {
        this.listener = listener
    }

    private var unChecked: Drawable? = null
    private var checked: Drawable? = null
    private var checkBoxWidth: Float
    private var checkBoxHeight: Float

    private var textWidth: String?
    private var textHeight: String?
    private var text: String?
    private var fontSize: Float
    private var textColor: Int
    private var textFont: Typeface? = null

    init {
        binding = CustomviewCheckboxBinding.inflate(LayoutInflater.from(context), this, true)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CheckBoxCustomView)

        val c_layoutParams = binding.checkBox.layoutParams

        val t_layoutParams = binding.textView.layoutParams

        checkBoxWidth = typedArray.getDimension(R.styleable.CheckBoxCustomView_c_width, 20F)
        checkBoxHeight = typedArray.getDimension(R.styleable.CheckBoxCustomView_c_height, 20F)

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

        binding.checkBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            compoundButton.setBackgroundResource(if (isChecked) R.drawable.checked else R.drawable.unchecked)
            listener?.onCheckChange(compoundButton, isChecked)
        }

        c_layoutParams.width = checkBoxWidth.toInt()
        c_layoutParams.height = checkBoxHeight.toInt()
        binding.checkBox.layoutParams = c_layoutParams

        binding.checkBox.background = unChecked

        textWidth = typedArray.getString(R.styleable.CheckBoxCustomView_t_width)
        textHeight = typedArray.getString(R.styleable.CheckBoxCustomView_t_height)

        text = typedArray.getString(R.styleable.CheckBoxCustomView_t_text)
        fontSize = typedArray.getDimension(R.styleable.CheckBoxCustomView_t_fontSize,16F)
        textColor = typedArray.getColor(R.styleable.CheckBoxCustomView_t_color, Color.BLACK)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textFont = typedArray.getFont(R.styleable.CheckBoxCustomView_t_font)
        }

        t_layoutParams.width = if (textWidth == "wrap") LayoutParams.WRAP_CONTENT
        else typedArray.getDimension(R.styleable.CheckBoxCustomView_t_width, 20F).toInt()

        t_layoutParams.height = if (textHeight == "wrap") LayoutParams.WRAP_CONTENT
        else typedArray.getDimension(R.styleable.CheckBoxCustomView_t_height, 20F).toInt()

        binding.textView.layoutParams = t_layoutParams

        val textView = binding.textView

        textView.text = text
        textView.textSize = fontSize
        textView.setTextColor(textColor)
        textView.typeface = textFont
    }

    fun isChecked(): Boolean{
        return binding.checkBox.isChecked
    }

}
package com.mabinogifanmade.squiretracker.utils.views

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.google.android.material.textfield.TextInputEditText


class ClearableTextInputEditText(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    TextInputEditText(context, attrs, defStyleAttr) {
    var iconVisibility: Boolean = false;

    constructor(context: Context?) : this(context, null as AttributeSet?) {}

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, android.R.attr.editTextStyle) {}

    init {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    if (s!!.length > 0) {
                        setClearDrawable(true)
                    } else {
                        setClearDrawable(false)
                    }
                } else {
                    setClearDrawable(false)
                }
            }


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        this.setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                var hasConsumed = false
                if (iconVisibility) {
                    val totalPaddingRight = this@ClearableTextInputEditText.paddingRight +
                            this@ClearableTextInputEditText.compoundDrawables[2].intrinsicWidth
                    if (event!!.x >= v!!.width - totalPaddingRight) {
                        if (event.action == MotionEvent.ACTION_DOWN) {
                            this@ClearableTextInputEditText.setText("")
                        }
                        hasConsumed = true
                    }
                }
                return hasConsumed
            }
        })
    }

    fun setClearDrawable(visible: Boolean) {
        iconVisibility = visible
        this.setCompoundDrawablesWithIntrinsicBounds(
            0, 0,
            when (iconVisibility) {
                true -> com.mabinogifanmade.squiretracker.R.drawable.ic_clear
                else -> 0
            }, 0
        )

    }

}
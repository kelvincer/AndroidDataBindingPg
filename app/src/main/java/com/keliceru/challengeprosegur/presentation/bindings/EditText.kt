package com.keliceru.challengeprosegur.presentation.bindings

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.keliceru.challengeprosegur.R

@BindingAdapter("android:inputType")
fun EditText.setInputType(inputType: String?) {
    when (inputType) {
        "text" -> setInputType(InputType.TYPE_CLASS_TEXT)
        "number" -> setInputType(InputType.TYPE_CLASS_NUMBER)
        "numberDecimal" -> setInputType(
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        )
    }
}

@BindingAdapter("textInput", "availableSeats", "seat")
fun EditText.setErrorMessage(
    textInputLayout: TextInputLayout,
    availableSeats: Int,
    fieldSeat: Boolean,
) {
    setOnFocusChangeListener { view, focus ->
        if (!focus) {
            if (text.isBlank()) {
                textInputLayout.helperText = context.getString(R.string.required_field)
            }
        }
    }

    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            if (p0.toString().isNotBlank()) {
                textInputLayout.helperText = ""
                if (fieldSeat) {
                    if (p0.toString().toInt() > availableSeats) {
                        textInputLayout.helperText =
                            context.getString(R.string.available_seats_exceed)
                    }
                }
            }
        }
    })
}

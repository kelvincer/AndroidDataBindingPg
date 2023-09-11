package com.keliceru.challengeprosegur.presentation.bindings

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("helperText")
fun TextInputLayout.setHelperText(value: String) {
    if (hasFocus()) {
        if (value.isBlank())
            helperText = "Error"
        else
            helperText = ""
    } else {
        helperText = ""
    }

}
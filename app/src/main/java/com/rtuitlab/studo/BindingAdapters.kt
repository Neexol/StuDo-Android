package com.rtuitlab.studo

import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.rtuitlab.studo.custom_views.AvatarView


@BindingAdapter("handleErrorFrom")
fun TextInputLayout.handleError(error: String) {
    if (error.isNotEmpty()) {
        this.isErrorEnabled = true
        this.error = error
    } else {
        this.isErrorEnabled = false
    }
}

@BindingAdapter("bindText")
fun AvatarView.setText(text: String) {
    this.text = text
    this.invalidate()
}

@BindingAdapter("bindAfterTextChanged")
fun EditText.setAfterTextChangedListener(func: () -> Unit) {
    this.addTextChangedListener {
        doAfterTextChanged {
            func.invoke()
        }
    }
}

@BindingAdapter("isShow")
fun FloatingActionButton.isShow(isShow: Boolean) {
    if (isShow) {
        this.show()
    } else {
        this.hide()
    }
}

@BindingAdapter("isShow")
fun ExtendedFloatingActionButton.isShow(isShow: Boolean) {
    if (isShow) {
        this.show()
    } else {
        this.hide()
    }
}

@BindingAdapter("isEnabled")
fun ImageView.isEnabled(isEnabled: Boolean) {
    if (isEnabled) {
        DrawableCompat.setTint(this.drawable, ContextCompat.getColor(context, R.color.colorPrimary))
        this.isClickable = true
        this.isFocusable = true
    } else {
        DrawableCompat.setTint(this.drawable, ContextCompat.getColor(context, R.color.colorDisable))
        this.isClickable = false
        this.isFocusable = false
    }
}
// Generated by view binder compiler. Do not edit!
package com.application.mypet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.application.mypet.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPasswordRecoveryBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText editTextTextPersonName2;

  @NonNull
  public final EditText editTextTextPersonName3;

  @NonNull
  public final Button enterButton;

  @NonNull
  public final ToolbarWithBackBinding include2;

  @NonNull
  public final ConstraintLayout intConstLayout;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  private ActivityPasswordRecoveryBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText editTextTextPersonName2, @NonNull EditText editTextTextPersonName3,
      @NonNull Button enterButton, @NonNull ToolbarWithBackBinding include2,
      @NonNull ConstraintLayout intConstLayout, @NonNull TextView textView,
      @NonNull TextView textView6, @NonNull TextView textView7, @NonNull TextView textView8) {
    this.rootView = rootView;
    this.editTextTextPersonName2 = editTextTextPersonName2;
    this.editTextTextPersonName3 = editTextTextPersonName3;
    this.enterButton = enterButton;
    this.include2 = include2;
    this.intConstLayout = intConstLayout;
    this.textView = textView;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.textView8 = textView8;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPasswordRecoveryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPasswordRecoveryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_password_recovery, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPasswordRecoveryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.editTextTextPersonName2;
      EditText editTextTextPersonName2 = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName2 == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName3;
      EditText editTextTextPersonName3 = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName3 == null) {
        break missingId;
      }

      id = R.id.enter_button;
      Button enterButton = ViewBindings.findChildViewById(rootView, id);
      if (enterButton == null) {
        break missingId;
      }

      id = R.id.include2;
      View include2 = ViewBindings.findChildViewById(rootView, id);
      if (include2 == null) {
        break missingId;
      }
      ToolbarWithBackBinding binding_include2 = ToolbarWithBackBinding.bind(include2);

      id = R.id.int_const_layout;
      ConstraintLayout intConstLayout = ViewBindings.findChildViewById(rootView, id);
      if (intConstLayout == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      return new ActivityPasswordRecoveryBinding((ConstraintLayout) rootView,
          editTextTextPersonName2, editTextTextPersonName3, enterButton, binding_include2,
          intConstLayout, textView, textView6, textView7, textView8);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

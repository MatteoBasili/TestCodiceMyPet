// Generated by view binder compiler. Do not edit!
package com.application.mypet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public final class FragmentSearchBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView findPetImage;

  @NonNull
  public final ImageView findPetSitterImage;

  @NonNull
  public final TextView findPetSitterText;

  @NonNull
  public final View findPetSitterView;

  @NonNull
  public final TextView findPetText;

  @NonNull
  public final ImageView findShopImage;

  @NonNull
  public final TextView findShopText;

  @NonNull
  public final ImageView findVetImage;

  @NonNull
  public final TextView findVetText;

  @NonNull
  public final ConstraintLayout intConstLayout;

  @NonNull
  public final View shopsView;

  @NonNull
  public final TextView textView;

  @NonNull
  public final DefaultToolbarBinding toolbar;

  @NonNull
  public final View veterinariansView;

  @NonNull
  public final View view;

  private FragmentSearchBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView findPetImage,
      @NonNull ImageView findPetSitterImage, @NonNull TextView findPetSitterText,
      @NonNull View findPetSitterView, @NonNull TextView findPetText,
      @NonNull ImageView findShopImage, @NonNull TextView findShopText,
      @NonNull ImageView findVetImage, @NonNull TextView findVetText,
      @NonNull ConstraintLayout intConstLayout, @NonNull View shopsView, @NonNull TextView textView,
      @NonNull DefaultToolbarBinding toolbar, @NonNull View veterinariansView, @NonNull View view) {
    this.rootView = rootView;
    this.findPetImage = findPetImage;
    this.findPetSitterImage = findPetSitterImage;
    this.findPetSitterText = findPetSitterText;
    this.findPetSitterView = findPetSitterView;
    this.findPetText = findPetText;
    this.findShopImage = findShopImage;
    this.findShopText = findShopText;
    this.findVetImage = findVetImage;
    this.findVetText = findVetText;
    this.intConstLayout = intConstLayout;
    this.shopsView = shopsView;
    this.textView = textView;
    this.toolbar = toolbar;
    this.veterinariansView = veterinariansView;
    this.view = view;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_search, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSearchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.find_pet_image;
      ImageView findPetImage = ViewBindings.findChildViewById(rootView, id);
      if (findPetImage == null) {
        break missingId;
      }

      id = R.id.find_pet_sitter_image;
      ImageView findPetSitterImage = ViewBindings.findChildViewById(rootView, id);
      if (findPetSitterImage == null) {
        break missingId;
      }

      id = R.id.find_pet_sitter_text;
      TextView findPetSitterText = ViewBindings.findChildViewById(rootView, id);
      if (findPetSitterText == null) {
        break missingId;
      }

      id = R.id.find_pet_sitter_view;
      View findPetSitterView = ViewBindings.findChildViewById(rootView, id);
      if (findPetSitterView == null) {
        break missingId;
      }

      id = R.id.find_pet_text;
      TextView findPetText = ViewBindings.findChildViewById(rootView, id);
      if (findPetText == null) {
        break missingId;
      }

      id = R.id.find_shop_image;
      ImageView findShopImage = ViewBindings.findChildViewById(rootView, id);
      if (findShopImage == null) {
        break missingId;
      }

      id = R.id.find_shop_text;
      TextView findShopText = ViewBindings.findChildViewById(rootView, id);
      if (findShopText == null) {
        break missingId;
      }

      id = R.id.find_vet_image;
      ImageView findVetImage = ViewBindings.findChildViewById(rootView, id);
      if (findVetImage == null) {
        break missingId;
      }

      id = R.id.find_vet_text;
      TextView findVetText = ViewBindings.findChildViewById(rootView, id);
      if (findVetText == null) {
        break missingId;
      }

      id = R.id.int_const_layout;
      ConstraintLayout intConstLayout = ViewBindings.findChildViewById(rootView, id);
      if (intConstLayout == null) {
        break missingId;
      }

      id = R.id.shops_view;
      View shopsView = ViewBindings.findChildViewById(rootView, id);
      if (shopsView == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.toolbar;
      View toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }
      DefaultToolbarBinding binding_toolbar = DefaultToolbarBinding.bind(toolbar);

      id = R.id.veterinarians_view;
      View veterinariansView = ViewBindings.findChildViewById(rootView, id);
      if (veterinariansView == null) {
        break missingId;
      }

      id = R.id.view;
      View view = ViewBindings.findChildViewById(rootView, id);
      if (view == null) {
        break missingId;
      }

      return new FragmentSearchBinding((ConstraintLayout) rootView, findPetImage,
          findPetSitterImage, findPetSitterText, findPetSitterView, findPetText, findShopImage,
          findShopText, findVetImage, findVetText, intConstLayout, shopsView, textView,
          binding_toolbar, veterinariansView, view);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

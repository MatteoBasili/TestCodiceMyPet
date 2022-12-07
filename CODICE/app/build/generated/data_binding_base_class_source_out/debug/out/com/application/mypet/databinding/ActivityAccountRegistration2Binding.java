// Generated by view binder compiler. Do not edit!
package com.application.mypet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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

public final class ActivityAccountRegistration2Binding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView address;

  @NonNull
  public final EditText addressInput;

  @NonNull
  public final TextView caredPets;

  @NonNull
  public final CheckBox caredPetsCheckBox1;

  @NonNull
  public final CheckBox caredPetsCheckBox2;

  @NonNull
  public final CheckBox caredPetsCheckBox3;

  @NonNull
  public final Button createAccountButton;

  @NonNull
  public final TextView homeNumb;

  @NonNull
  public final EditText homeNumbInput;

  @NonNull
  public final ConstraintLayout intConstLayout;

  @NonNull
  public final TextView offeredServices;

  @NonNull
  public final CheckBox petSitterCheckBox;

  @NonNull
  public final TextView phoneNumb;

  @NonNull
  public final EditText phoneNumbInput;

  @NonNull
  public final TextView place;

  @NonNull
  public final EditText placeInput;

  @NonNull
  public final TextView postalCode;

  @NonNull
  public final EditText postalCodeInput;

  @NonNull
  public final TextView province;

  @NonNull
  public final EditText provinceInput;

  @NonNull
  public final CheckBox servicesCheckBox1;

  @NonNull
  public final CheckBox servicesCheckBox2;

  @NonNull
  public final CheckBox servicesCheckBox3;

  @NonNull
  public final CheckBox servicesCheckBox4;

  @NonNull
  public final CheckBox servicesCheckBox5;

  @NonNull
  public final TextView subtitle;

  @NonNull
  public final TextView title;

  @NonNull
  public final ToolbarWithBackBinding toolbar;

  @NonNull
  public final TextView username;

  @NonNull
  public final EditText usernameInput;

  private ActivityAccountRegistration2Binding(@NonNull ConstraintLayout rootView,
      @NonNull TextView address, @NonNull EditText addressInput, @NonNull TextView caredPets,
      @NonNull CheckBox caredPetsCheckBox1, @NonNull CheckBox caredPetsCheckBox2,
      @NonNull CheckBox caredPetsCheckBox3, @NonNull Button createAccountButton,
      @NonNull TextView homeNumb, @NonNull EditText homeNumbInput,
      @NonNull ConstraintLayout intConstLayout, @NonNull TextView offeredServices,
      @NonNull CheckBox petSitterCheckBox, @NonNull TextView phoneNumb,
      @NonNull EditText phoneNumbInput, @NonNull TextView place, @NonNull EditText placeInput,
      @NonNull TextView postalCode, @NonNull EditText postalCodeInput, @NonNull TextView province,
      @NonNull EditText provinceInput, @NonNull CheckBox servicesCheckBox1,
      @NonNull CheckBox servicesCheckBox2, @NonNull CheckBox servicesCheckBox3,
      @NonNull CheckBox servicesCheckBox4, @NonNull CheckBox servicesCheckBox5,
      @NonNull TextView subtitle, @NonNull TextView title, @NonNull ToolbarWithBackBinding toolbar,
      @NonNull TextView username, @NonNull EditText usernameInput) {
    this.rootView = rootView;
    this.address = address;
    this.addressInput = addressInput;
    this.caredPets = caredPets;
    this.caredPetsCheckBox1 = caredPetsCheckBox1;
    this.caredPetsCheckBox2 = caredPetsCheckBox2;
    this.caredPetsCheckBox3 = caredPetsCheckBox3;
    this.createAccountButton = createAccountButton;
    this.homeNumb = homeNumb;
    this.homeNumbInput = homeNumbInput;
    this.intConstLayout = intConstLayout;
    this.offeredServices = offeredServices;
    this.petSitterCheckBox = petSitterCheckBox;
    this.phoneNumb = phoneNumb;
    this.phoneNumbInput = phoneNumbInput;
    this.place = place;
    this.placeInput = placeInput;
    this.postalCode = postalCode;
    this.postalCodeInput = postalCodeInput;
    this.province = province;
    this.provinceInput = provinceInput;
    this.servicesCheckBox1 = servicesCheckBox1;
    this.servicesCheckBox2 = servicesCheckBox2;
    this.servicesCheckBox3 = servicesCheckBox3;
    this.servicesCheckBox4 = servicesCheckBox4;
    this.servicesCheckBox5 = servicesCheckBox5;
    this.subtitle = subtitle;
    this.title = title;
    this.toolbar = toolbar;
    this.username = username;
    this.usernameInput = usernameInput;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAccountRegistration2Binding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAccountRegistration2Binding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_account_registration2, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAccountRegistration2Binding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address;
      TextView address = ViewBindings.findChildViewById(rootView, id);
      if (address == null) {
        break missingId;
      }

      id = R.id.address_input;
      EditText addressInput = ViewBindings.findChildViewById(rootView, id);
      if (addressInput == null) {
        break missingId;
      }

      id = R.id.cared_pets;
      TextView caredPets = ViewBindings.findChildViewById(rootView, id);
      if (caredPets == null) {
        break missingId;
      }

      id = R.id.cared_pets_checkBox1;
      CheckBox caredPetsCheckBox1 = ViewBindings.findChildViewById(rootView, id);
      if (caredPetsCheckBox1 == null) {
        break missingId;
      }

      id = R.id.cared_pets_checkBox2;
      CheckBox caredPetsCheckBox2 = ViewBindings.findChildViewById(rootView, id);
      if (caredPetsCheckBox2 == null) {
        break missingId;
      }

      id = R.id.cared_pets_checkBox3;
      CheckBox caredPetsCheckBox3 = ViewBindings.findChildViewById(rootView, id);
      if (caredPetsCheckBox3 == null) {
        break missingId;
      }

      id = R.id.create_account_button;
      Button createAccountButton = ViewBindings.findChildViewById(rootView, id);
      if (createAccountButton == null) {
        break missingId;
      }

      id = R.id.home_numb;
      TextView homeNumb = ViewBindings.findChildViewById(rootView, id);
      if (homeNumb == null) {
        break missingId;
      }

      id = R.id.home_numb_input;
      EditText homeNumbInput = ViewBindings.findChildViewById(rootView, id);
      if (homeNumbInput == null) {
        break missingId;
      }

      id = R.id.int_const_layout;
      ConstraintLayout intConstLayout = ViewBindings.findChildViewById(rootView, id);
      if (intConstLayout == null) {
        break missingId;
      }

      id = R.id.offered_services;
      TextView offeredServices = ViewBindings.findChildViewById(rootView, id);
      if (offeredServices == null) {
        break missingId;
      }

      id = R.id.pet_sitter_checkBox;
      CheckBox petSitterCheckBox = ViewBindings.findChildViewById(rootView, id);
      if (petSitterCheckBox == null) {
        break missingId;
      }

      id = R.id.phone_numb;
      TextView phoneNumb = ViewBindings.findChildViewById(rootView, id);
      if (phoneNumb == null) {
        break missingId;
      }

      id = R.id.phone_numb_input;
      EditText phoneNumbInput = ViewBindings.findChildViewById(rootView, id);
      if (phoneNumbInput == null) {
        break missingId;
      }

      id = R.id.place;
      TextView place = ViewBindings.findChildViewById(rootView, id);
      if (place == null) {
        break missingId;
      }

      id = R.id.place_input;
      EditText placeInput = ViewBindings.findChildViewById(rootView, id);
      if (placeInput == null) {
        break missingId;
      }

      id = R.id.postal_code;
      TextView postalCode = ViewBindings.findChildViewById(rootView, id);
      if (postalCode == null) {
        break missingId;
      }

      id = R.id.postal_code_input;
      EditText postalCodeInput = ViewBindings.findChildViewById(rootView, id);
      if (postalCodeInput == null) {
        break missingId;
      }

      id = R.id.province;
      TextView province = ViewBindings.findChildViewById(rootView, id);
      if (province == null) {
        break missingId;
      }

      id = R.id.province_input;
      EditText provinceInput = ViewBindings.findChildViewById(rootView, id);
      if (provinceInput == null) {
        break missingId;
      }

      id = R.id.services_checkBox1;
      CheckBox servicesCheckBox1 = ViewBindings.findChildViewById(rootView, id);
      if (servicesCheckBox1 == null) {
        break missingId;
      }

      id = R.id.services_checkBox2;
      CheckBox servicesCheckBox2 = ViewBindings.findChildViewById(rootView, id);
      if (servicesCheckBox2 == null) {
        break missingId;
      }

      id = R.id.services_checkBox3;
      CheckBox servicesCheckBox3 = ViewBindings.findChildViewById(rootView, id);
      if (servicesCheckBox3 == null) {
        break missingId;
      }

      id = R.id.services_checkBox4;
      CheckBox servicesCheckBox4 = ViewBindings.findChildViewById(rootView, id);
      if (servicesCheckBox4 == null) {
        break missingId;
      }

      id = R.id.services_checkBox5;
      CheckBox servicesCheckBox5 = ViewBindings.findChildViewById(rootView, id);
      if (servicesCheckBox5 == null) {
        break missingId;
      }

      id = R.id.subtitle;
      TextView subtitle = ViewBindings.findChildViewById(rootView, id);
      if (subtitle == null) {
        break missingId;
      }

      id = R.id.title;
      TextView title = ViewBindings.findChildViewById(rootView, id);
      if (title == null) {
        break missingId;
      }

      id = R.id.toolbar;
      View toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }
      ToolbarWithBackBinding binding_toolbar = ToolbarWithBackBinding.bind(toolbar);

      id = R.id.username;
      TextView username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      id = R.id.username_input;
      EditText usernameInput = ViewBindings.findChildViewById(rootView, id);
      if (usernameInput == null) {
        break missingId;
      }

      return new ActivityAccountRegistration2Binding((ConstraintLayout) rootView, address,
          addressInput, caredPets, caredPetsCheckBox1, caredPetsCheckBox2, caredPetsCheckBox3,
          createAccountButton, homeNumb, homeNumbInput, intConstLayout, offeredServices,
          petSitterCheckBox, phoneNumb, phoneNumbInput, place, placeInput, postalCode,
          postalCodeInput, province, provinceInput, servicesCheckBox1, servicesCheckBox2,
          servicesCheckBox3, servicesCheckBox4, servicesCheckBox5, subtitle, title, binding_toolbar,
          username, usernameInput);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

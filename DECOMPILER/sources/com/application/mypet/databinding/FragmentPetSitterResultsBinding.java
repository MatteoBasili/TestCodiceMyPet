package com.application.mypet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.application.mypet.R;

public final class FragmentPetSitterResultsBinding implements ViewBinding {
    public final ConstraintLayout constView;
    public final ConstraintLayout intConstView;
    public final ProgressBar loadProgressBar;
    public final ListView petSitListView;
    private final ConstraintLayout rootView;
    public final TextView subtitle;
    public final TextView textNoResults;
    public final TextView title;
    public final ToolbarWithBackBinding toolbar;

    private FragmentPetSitterResultsBinding(ConstraintLayout rootView2, ConstraintLayout constView2, ConstraintLayout intConstView2, ProgressBar loadProgressBar2, ListView petSitListView2, TextView subtitle2, TextView textNoResults2, TextView title2, ToolbarWithBackBinding toolbar2) {
        this.rootView = rootView2;
        this.constView = constView2;
        this.intConstView = intConstView2;
        this.loadProgressBar = loadProgressBar2;
        this.petSitListView = petSitListView2;
        this.subtitle = subtitle2;
        this.textNoResults = textNoResults2;
        this.title = title2;
        this.toolbar = toolbar2;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static FragmentPetSitterResultsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static FragmentPetSitterResultsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.fragment_pet_sitter_results, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static FragmentPetSitterResultsBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.constView;
        ConstraintLayout constView2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.constView);
        if (constView2 != null) {
            id = R.id.intConstView;
            ConstraintLayout intConstView2 = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.intConstView);
            if (intConstView2 != null) {
                id = R.id.load_progressBar;
                ProgressBar loadProgressBar2 = (ProgressBar) ViewBindings.findChildViewById(view, R.id.load_progressBar);
                if (loadProgressBar2 != null) {
                    id = R.id.petSitListView;
                    ListView petSitListView2 = (ListView) ViewBindings.findChildViewById(view, R.id.petSitListView);
                    if (petSitListView2 != null) {
                        id = R.id.subtitle;
                        TextView subtitle2 = (TextView) ViewBindings.findChildViewById(view, R.id.subtitle);
                        if (subtitle2 != null) {
                            id = R.id.textNoResults;
                            TextView textNoResults2 = (TextView) ViewBindings.findChildViewById(view, R.id.textNoResults);
                            if (textNoResults2 != null) {
                                id = R.id.title;
                                TextView title2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                                if (title2 != null) {
                                    id = R.id.toolbar;
                                    View toolbar2 = ViewBindings.findChildViewById(view, R.id.toolbar);
                                    if (toolbar2 != null) {
                                        return new FragmentPetSitterResultsBinding((ConstraintLayout) view, constView2, intConstView2, loadProgressBar2, petSitListView2, subtitle2, textNoResults2, title2, ToolbarWithBackBinding.bind(toolbar2));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}

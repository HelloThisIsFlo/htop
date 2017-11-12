package com.floriankempenich.htop.presentation;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.floriankempenich.htop.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @AfterViews
    void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    @Click(R.id.fab)
    public void handleFABClick() {
        showSnackbar("Replace w/ your own action");
    }

    private void showSnackbar(String message) {
        Snackbar.make(fab, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @OptionsItem(R.id.action_settings)
    void openSettings() {
        showSnackbar("Open settings :D :D ");
    }

}

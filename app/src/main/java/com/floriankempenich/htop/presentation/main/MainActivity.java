package com.floriankempenich.htop.presentation.main;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.floriankempenich.htop.R;
import com.floriankempenich.htop.domain.application.process.ProcessDTO;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Bean(MainPresenter.class)
    MainContract.Presenter presenter;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @AfterInject
    void initPresenter() {
        presenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @AfterViews
    void setupToolbar() {
        setSupportActionBar(toolbar);
    }


    @Click(R.id.fab)
    public void handleFABClick() {
        presenter.refreshProcesses();
    }

    private void showSnackbar(String message) {
        Snackbar.make(fab, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @OptionsItem(R.id.action_settings)
    void openSettings() {
        showSnackbar("Open settings :D :D ");
    }

    @Override
    public void refreshProcessList(List<ProcessDTO> processes) {

    }

    @Override
    public void showListLoading() {
    }

    @Override
    public void hideListLoading() {
    }

    @Override
    public void showShortToast(String message) {
        Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
        ).show();
    }
}

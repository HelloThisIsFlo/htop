package com.floriankempenich.htop.presentation.main;

import com.floriankempenich.htop.presentation.BaseView;

import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void refreshProcesses() {

        view.showShortToast("Refreshing processes :D :D");

    }

}

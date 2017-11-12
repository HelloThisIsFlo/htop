package com.floriankempenich.htop.presentation.main;


import com.floriankempenich.htop.domain.application.process.ProcessDTO;
import com.floriankempenich.htop.presentation.BasePresenter;
import com.floriankempenich.htop.presentation.BaseView;

import java.util.List;

public interface MainContract {

    interface View extends BaseView {
        void refreshProcessList(List<ProcessDTO> processes);

        void showListLoading();
        void hideListLoading();

        void showShortToast(String message);
    }

    interface Presenter extends BasePresenter<MainContract.View> {
        void refreshProcesses();
    }

}

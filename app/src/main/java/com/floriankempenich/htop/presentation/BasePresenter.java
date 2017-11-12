package com.floriankempenich.htop.presentation;

public interface BasePresenter<ViewT extends BaseView> {

    void setView(ViewT view);
    void start();

}

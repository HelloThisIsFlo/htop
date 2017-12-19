package com.floriankempenich.htop.domain.application;


public interface ProcessSnapshotService {

    /**
     * Store a snapshot for each processe currently running.
     */
    void storeSnapshots();

}

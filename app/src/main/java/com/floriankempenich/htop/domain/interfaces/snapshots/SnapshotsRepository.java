package com.floriankempenich.htop.domain.interfaces.snapshots;


import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotDTO;

public interface SnapshotsRepository {

    void saveSnapshot(ProcessSnapshotDTO snapshot);


}

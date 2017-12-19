package com.floriankempenich.htop.module.snapshots;

import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotDTO;
import com.floriankempenich.htop.domain.interfaces.snapshots.SnapshotsRepository;

public class InMemorySnapshotsRepository implements SnapshotsRepository {

    @Override
    public void saveSnapshot(ProcessSnapshotDTO snapshot) {

    }
}

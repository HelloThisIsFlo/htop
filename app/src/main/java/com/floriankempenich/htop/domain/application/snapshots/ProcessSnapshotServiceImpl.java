package com.floriankempenich.htop.domain.application.snapshots;

import com.floriankempenich.htop.domain.application.ProcessSnapshotService;
import com.floriankempenich.htop.domain.interfaces.snapshots.NativePs;
import com.floriankempenich.htop.domain.interfaces.snapshots.SnapshotsRepository;


public class ProcessSnapshotServiceImpl implements ProcessSnapshotService {

    private final SnapshotsRepository snapshotsRepository;
    private final NativePs nativePs;

    public ProcessSnapshotServiceImpl(SnapshotsRepository snapshotsRepository, NativePs nativePs) {
        this.snapshotsRepository = snapshotsRepository;
        this.nativePs = nativePs;
    }

    @Override
    public void storeSnapshots() {
        nativePs.ps()
                .forEach(snapshotsRepository::saveSnapshot);
    }


}

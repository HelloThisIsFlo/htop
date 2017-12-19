package com.floriankempenich.htop.domain.interfaces.snapshots;


import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotDTO;

import java.util.List;

public interface NativePs {

    /**
     * Executes the native `ps` command on the android device.
     *
     * @return Snapshots for each process currently running.
     */
    List<ProcessSnapshotDTO> ps();
}

package com.floriankempenich.htop;


import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotDTO;
import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotId;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class ProcessSnapshotObjectMother {

    public static ProcessSnapshotDTO aRandomSnapshot(ProcessSnapshotId id) {
        Random rand = new Random();
        return new ProcessSnapshotDTO(
                id,
                Duration.ofSeconds(rand.nextInt()),
                Duration.ofSeconds(rand.nextInt()),
                "Random String: " + rand.nextInt()
        );
    }

}

package com.floriankempenich.htop.module.snapshots;

import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotDTO;
import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotId;
import com.floriankempenich.htop.domain.interfaces.snapshots.SnapshotsRepository;

import org.androidannotations.annotations.EBean;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@EBean(scope = EBean.Scope.Singleton)
public class InMemorySnapshotsRepository implements SnapshotsRepository {

    private final List<ProcessSnapshotDTO> datastore;

    public InMemorySnapshotsRepository() {
        datastore = new ArrayList<>();
    }

    @Override
    public void saveSnapshot(ProcessSnapshotDTO snapshot) {
        datastore.add(snapshot);
    }

    @Override
    public ProcessSnapshotDTO get(ProcessSnapshotId id) {
        return datastore.stream()
                .filter(snapshot -> snapshot.id.equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ProcessSnapshotDTO> getDuringPeriod(Long pid, Period period) {
        return datastore.stream()
                .filter(snapshot -> snapshot.id.pid.equals(pid))
                .filter(snapshot -> isInPeriod(snapshot, period))
                .sorted(this::snapshotComparator)
                .collect(Collectors.toList());
    }

    private boolean isInPeriod(ProcessSnapshotDTO snapshot, Period period) {
        Instant snapshotTime = snapshot.id.timestamp;
        return (snapshotTime.isAfter(period.startInclusive) || snapshotTime.equals(period.startInclusive))
                && (snapshotTime.isBefore(period.endInclusive) || snapshotTime.equals(period.endInclusive));
    }

    private int snapshotComparator(ProcessSnapshotDTO first, ProcessSnapshotDTO second) {
        return first.id.timestamp.compareTo(second.id.timestamp);
    }
}

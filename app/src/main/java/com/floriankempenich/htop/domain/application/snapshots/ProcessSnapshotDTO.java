package com.floriankempenich.htop.domain.application.snapshots;


import java.time.Duration;
import java.util.Objects;

/**
 * Represents a Process Snapshots.
 * 2 snapshots are equal if they share the same id.
 */
public class ProcessSnapshotDTO {

    public final ProcessSnapshotId id;

    public final Duration cpuTime;
    public final Duration lifeTime;

    public final String name;

    public ProcessSnapshotDTO(ProcessSnapshotId id, Duration cpuTime, Duration lifeTime, String name) {
        this.id = id;
        this.cpuTime = cpuTime;
        this.lifeTime = lifeTime;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessSnapshotDTO that = (ProcessSnapshotDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProcessSnapshotDTO{");
        sb.append("id=").append(id);
        sb.append(", cpuTime=").append(cpuTime);
        sb.append(", lifeTime=").append(lifeTime);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.floriankempenich.htop.domain.application.snapshots;

import java.time.Instant;
import java.util.Objects;

/**
 * Represents a Process Snapshots ID.
 */
public class ProcessSnapshotId {

    public final Long pid;
    public final Instant timestamp;

    public ProcessSnapshotId(Long pid, Instant timestamp) {
        this.pid = pid;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessSnapshotId that = (ProcessSnapshotId) o;
        return Objects.equals(pid, that.pid) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, timestamp);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProcessSnapshotId{");
        sb.append("pid=").append(pid);
        sb.append(", timestamp=").append(timestamp);
        sb.append('}');
        return sb.toString();
    }
}

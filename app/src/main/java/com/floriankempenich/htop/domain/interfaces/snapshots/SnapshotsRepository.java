package com.floriankempenich.htop.domain.interfaces.snapshots;


import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotDTO;
import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotId;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public interface SnapshotsRepository {

    void saveSnapshot(ProcessSnapshotDTO snapshot);

    ProcessSnapshotDTO get(ProcessSnapshotId id);

    List<ProcessSnapshotDTO> getDuringPeriod(Long pid, Period period);

    class Period {
        public final Instant startInclusive;
        public final Instant endInclusive;


        public Period(Instant startInclusive, Instant endInclusive) {
            this.startInclusive = startInclusive;
            this.endInclusive = endInclusive;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return Objects.equals(startInclusive, period.startInclusive) &&
                    Objects.equals(endInclusive, period.endInclusive);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startInclusive, endInclusive);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Period{");
            sb.append("startInclusive=").append(startInclusive);
            sb.append(", endInclusive=").append(endInclusive);
            sb.append('}');
            return sb.toString();
        }
    }

}

package com.floriankempenich.htop.domain.application.snapshots;

import com.floriankempenich.htop.ProcessSnapshotObjectMother;

import org.junit.Test;

import java.time.Instant;

import static com.floriankempenich.htop.ProcessSnapshotObjectMother.aRandomSnapshot;
import static org.junit.Assert.*;

public class ProcessSnapshotDTOTest {

    @Test
    public void equalityIsOnIdOnly() throws Exception {
        // Given: 2 Snapshot with different data but same id
        ProcessSnapshotId id = new ProcessSnapshotId(43L, Instant.now());
        ProcessSnapshotDTO first = aRandomSnapshot(id);
        ProcessSnapshotDTO second = aRandomSnapshot(id);

        // Same id
        assertEquals(first.id, second.id);
        // Different data
        assertNotEquals(first.cpuTime, second.cpuTime);

        // Then: They are still considered equal
        // (Identity on id only)
        assertEquals(first, second);
    }
}
package com.floriankempenich.htop.domain.application.snapshots;

import com.floriankempenich.htop.domain.application.ProcessSnapshotService;
import com.floriankempenich.htop.domain.interfaces.snapshots.NativePs;
import com.floriankempenich.htop.domain.interfaces.snapshots.SnapshotsRepository;
import com.floriankempenich.htop.module.snapshots.InMemorySnapshotsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.floriankempenich.htop.ProcessSnapshotObjectMother.aRandomSnapshot;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProcessSnapshotServiceImplTest {
    @Mock
    NativePs mockNativePs;
    private ProcessSnapshotService snapshotService;
    private SnapshotsRepositorySpy snapshotsRepositorySpy;
    private List<ProcessSnapshotDTO> mockSnapshotList;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        snapshotsRepositorySpy = new SnapshotsRepositorySpy();
        snapshotService = new ProcessSnapshotServiceImpl(snapshotsRepositorySpy, mockNativePs);

        mockSnapshotList = Arrays.asList(
                aRandomSnapshot(new ProcessSnapshotId(1L, Instant.now())),
                aRandomSnapshot(new ProcessSnapshotId(1L, Instant.now().plusSeconds(3600))),
                aRandomSnapshot(new ProcessSnapshotId(2L, Instant.now()))
        );
    }

    @Test
    public void storesAllSnapshots() throws Exception {
        // Given: A mock snapshot list returned by the native service
        when(mockNativePs.ps()).thenReturn(mockSnapshotList);

        // When: Storing all Snapshots
        snapshotService.storeSnapshots();

        // Then: All snapshots are stored
        assertTrue(snapshotsRepositorySpy.savedSnapshots.containsAll(mockSnapshotList));
        assertEquals(mockSnapshotList.size(), snapshotsRepositorySpy.savedSnapshots.size());
    }

    private class SnapshotsRepositorySpy extends InMemorySnapshotsRepository {
        List<ProcessSnapshotDTO> savedSnapshots = new ArrayList<>();

        @Override
        public void saveSnapshot(ProcessSnapshotDTO snapshot) {
            super.saveSnapshot(snapshot);
            savedSnapshots.add(snapshot);
        }
    }
}
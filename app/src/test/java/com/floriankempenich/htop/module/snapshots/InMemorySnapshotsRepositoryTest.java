package com.floriankempenich.htop.module.snapshots;

import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotDTO;
import com.floriankempenich.htop.domain.application.snapshots.ProcessSnapshotId;
import com.floriankempenich.htop.domain.interfaces.snapshots.SnapshotsRepository;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.floriankempenich.htop.ProcessSnapshotObjectMother.aRandomSnapshot;
import static org.junit.Assert.assertEquals;

public class InMemorySnapshotsRepositoryTest {

    private SnapshotsRepository snapshotsRepository;

    private LocalDate today;
    private LocalDate yesterday;
    private LocalDate lastWeek;
    private LocalDate nextWeek;
    private LocalDate tomorrow;
    private LocalDate in2Weeks;


    @Before
    public void setUp() throws Exception {
        snapshotsRepository = new InMemorySnapshotsRepository();
        today = Instant.now().atOffset(ZoneOffset.UTC).toLocalDate();
        yesterday = today.minus(1, ChronoUnit.DAYS);
        lastWeek = today.minus(1, ChronoUnit.WEEKS);
        nextWeek = today.plus(1, ChronoUnit.WEEKS);
        tomorrow = today.plus(1, ChronoUnit.DAYS);
        in2Weeks = today.plus(2, ChronoUnit.WEEKS);
    }

    private ProcessSnapshotId id(long pid, Instant timestamp) {
        return new ProcessSnapshotId(pid, timestamp);
    }
    private ProcessSnapshotId id(long pid, LocalDate date) {
        return id(pid, date.atStartOfDay().toInstant(ZoneOffset.UTC));
    }
    private SnapshotsRepository.Period period(LocalDate startInclusive, LocalDate endInclusive) {
        return new SnapshotsRepository.Period(
                startInclusive.atStartOfDay().toInstant(ZoneOffset.UTC),
                endInclusive.atStartOfDay().toInstant(ZoneOffset.UTC)
        );
    }

    @Test
    public void saveAndRetrieveById() throws Exception {
        // Given: Snapshot is saved
        ProcessSnapshotDTO saved = aRandomSnapshot(id(4, Instant.now()));
        snapshotsRepository.saveSnapshot(saved);

        // When: Retrieving the snapshot
        ProcessSnapshotDTO fromRepo = snapshotsRepository.get(saved.id);

        // Then: Snapshot is found
        assertEquals(saved, fromRepo);
    }

    @Test
    public void processWithSameId_originalOverwritten() throws Exception {
        // Given: Snapshot is saved
        ProcessSnapshotId id = id(5, Instant.now());
        ProcessSnapshotDTO saved = aRandomSnapshot(id(4, Instant.now()));
        snapshotsRepository.saveSnapshot(saved);

        // When: Retrieving the snapshot
        ProcessSnapshotDTO fromRepo = snapshotsRepository.get(saved.id);

        // Then: Snapshot is found
        assertEquals(saved, fromRepo);
    }


    @Test
    public void getDuringPeriod_correctPeriod_sortedByDate() throws Exception {
        // Given: 3 snapshot saved:
        // - w/ same pid
        // - at following instants: yesterday, today, in2Weeks
        // - in "wrong" order
        ProcessSnapshotDTO todaySnapshot = aRandomSnapshot(id(1, today));
        ProcessSnapshotDTO yesterdaySnapshot = aRandomSnapshot(id(1, yesterday));
        ProcessSnapshotDTO in2WeeksSnapshot = aRandomSnapshot(id(1, in2Weeks));
        Stream.of(
                in2WeeksSnapshot,
                todaySnapshot,
                yesterdaySnapshot
        ).forEach(snapshotsRepository::saveSnapshot);

        // When: Retrieving snapshots between last week and next week
        SnapshotsRepository.Period period = period(lastWeek, nextWeek);
        List<ProcessSnapshotDTO> forPeriod = snapshotsRepository.getDuringPeriod(1L, period);

        // Then: Only snapshot for yesterday and today are returned
        assertEquals(2, forPeriod.size());
        assertEquals("In wrong order maybe?", yesterdaySnapshot, forPeriod.get(0));
        assertEquals("In wrong order maybe?", todaySnapshot, forPeriod.get(1));
    }

    @Test
    public void getDuringPeriod_correctPid() throws Exception {
        // Given: 2 snapshot saved w/ DIFFERENT PIDs
        //        Correct pid: 1
        ProcessSnapshotDTO todayCorrectPid = aRandomSnapshot(id(1, today));
        ProcessSnapshotDTO yesterdayWrongPid = aRandomSnapshot(id(3, yesterday));
        Stream.of(
                todayCorrectPid,
                yesterdayWrongPid
        ).forEach(snapshotsRepository::saveSnapshot);

        // When: Retrieving snapshots between last week and next week
        SnapshotsRepository.Period period = period(lastWeek, nextWeek);
        List<ProcessSnapshotDTO> forPeriod = snapshotsRepository.getDuringPeriod(1L, period);

        // Then: Only snapshot with correct PID is retrieved
        assertEquals(1, forPeriod.size());
        assertEquals(todayCorrectPid, forPeriod.get(0));
    }
}
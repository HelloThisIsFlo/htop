package com.floriankempenich.htop.domain.application.process;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProcessServiceImplTest {

    private ProcessServiceImpl processService;

    private List<ProcessDTO> mockProcess;

    @Before
    public void setUp() throws Exception {
        processService = new ProcessServiceImpl();

        // When getting processes, return mock list
        processService.setProcessesLister(() -> mockProcess);
    }

    private List<ProcessDTO> processesInRandomOrder() {
        return Arrays.asList(
                new ProcessDTO("second", 22F),
                new ProcessDTO("third", 33F),
                new ProcessDTO("first", 11F),
                new ProcessDTO("fourth", 44F)
        );
    }

    @Test
    public void noRunningProcesses_returnDefaultValues() throws Exception {
        // Given: No process
        mockProcess = Collections.emptyList();

        // Then: Default values
        assertEquals(null, processService.getMostCPUConsuming());
        assertEquals(Collections.emptyList(), processService.getRunningProcess());
    }

    @Test
    public void sortProcessByCPUConsumption() throws Exception {
        // Given: Process in random order
        mockProcess = processesInRandomOrder();

        // When: Getting process list
        List<ProcessDTO> fromService = processService.getRunningProcess();

        // Then: Process are sorted
        assertEquals("first", fromService.get(0).name);
        assertEquals("second", fromService.get(1).name);
        assertEquals("third", fromService.get(2).name);
        assertEquals("fourth", fromService.get(3).name);
    }

    @Test
    public void getMostCPUConsuming() throws Exception {
        // Given: Process in random order
        mockProcess = processesInRandomOrder();

        // When: Getting most consuming process
        ProcessDTO mostConsuming = processService.getMostCPUConsuming();

        // Then: Process are sorted
        assertEquals("first", mostConsuming.name);
    }
}
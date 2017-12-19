package com.floriankempenich.htop.domain.interfaces;


import com.floriankempenich.htop.domain.application.process.ProcessDTO;

import java.util.List;

/**
 * Expresses a need of the domain to have access to the list of running processes.
 *
 * FIXME: Read --> Important notes !!!
 * Basically it's not possible to just have the `cpu%` all ready to use.
 *
 * This information is not available on ps. Only the average `cpu%` in the lifetime of the process.
 * It makes sense when we think about it: `cpu%` . . . over what period?? `ps` can not guess that
 * for us.
 * `top` and `htop` are slow to run because ... they do the averaging themselves (I think)
 *
 * So we need to update the interface. Something like ==> 2 DTOs:
 * - ProcessSnapshotDTO(name, timestamp, cpuTime, lifeTime, etc...)
 * - ProcessCpuUsageDTO(name, cpuPercent, periodUsedForAvg, ...)
 *
 * 1 service would fill a repo with `ProcessSnapshotDTOs` regularly:
 * Basic architecture:
 *   - External daemon
 *       - Orchestrator service: ProcessSnapshotService => storeSnapshot();
 *           - External "PS" module
 *           - ProcessSnapshotDTO Repository
 *
 *
 * 1 other service would get all `ProcessSnapshotDTOs` (or all for a period) and do the required
 * analysis:
 * This service would be a domain service that has NO DEPENDENCY on the android shell.
 * It just analyses results from the ProcessSnapshotRepository:
 * Basic architecture:
 *   - Consumer of the domain (Presentation layer)
 *       - Orchestrator service: CpuUsageService => processSortedByUsage(Duration averagingPeriod)
 *                                                 :: List<ProcessCpuUsageDTO>
 *           - ProcessSnapshotRepository
 *
 * FIXME: Read --> Important notes !!!
 */
@FunctionalInterface
public interface DeprecatedRunningProcessesLister {

    /**
     * Get all running processes
     * @return List of running processes in no particular order.
     */
    List<ProcessDTO> getAll();

}

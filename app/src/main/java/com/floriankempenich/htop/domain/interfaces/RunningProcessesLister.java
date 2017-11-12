package com.floriankempenich.htop.domain.interfaces;


import com.floriankempenich.htop.domain.application.process.ProcessDTO;

import java.util.List;

/**
 * Expresses a need of the domain to have access to the list of running processes.
 */
public interface RunningProcessesLister {

    /**
     * Get all running processes
     * @return List of running processes in no particular order.
     */
    List<ProcessDTO> getAll();

}

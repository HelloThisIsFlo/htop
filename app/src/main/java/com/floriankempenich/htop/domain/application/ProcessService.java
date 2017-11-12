package com.floriankempenich.htop.domain.application;

import com.floriankempenich.htop.domain.application.process.ProcessDTO;

import java.util.List;

public interface ProcessService {

    List<ProcessDTO> getRunningProcess();
    ProcessDTO getMostCPUConsuming();

}

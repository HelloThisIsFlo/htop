package com.floriankempenich.htop.domain.application.process;

import com.floriankempenich.htop.domain.application.ProcessService;

import java.util.List;

public class ProcessServiceImpl implements ProcessService {

    @Override
    public List<ProcessDTO> getRunningProcess() {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public ProcessDTO getMostCPUConsuming() {
        throw new IllegalStateException("Not implemented yet");
    }

}

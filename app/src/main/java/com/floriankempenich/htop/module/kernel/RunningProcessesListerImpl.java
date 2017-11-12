package com.floriankempenich.htop.module.kernel;

import com.floriankempenich.htop.domain.application.process.ProcessDTO;
import com.floriankempenich.htop.domain.interfaces.RunningProcessesLister;

import java.util.List;

public class RunningProcessesListerImpl implements RunningProcessesLister {
    @Override
    public List<ProcessDTO> getAll() {
        throw new IllegalStateException("Not implemented yet");
    }
}

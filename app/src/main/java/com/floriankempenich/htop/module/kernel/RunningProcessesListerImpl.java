package com.floriankempenich.htop.module.kernel;

import com.floriankempenich.htop.domain.application.process.ProcessDTO;
import com.floriankempenich.htop.domain.interfaces.RunningProcessesLister;

import org.androidannotations.annotations.EBean;

import java.util.List;

@EBean(scope = EBean.Scope.Singleton)
public class RunningProcessesListerImpl implements RunningProcessesLister {
    @Override
    public List<ProcessDTO> getAll() {
        throw new IllegalStateException("Not implemented yet");
    }
}

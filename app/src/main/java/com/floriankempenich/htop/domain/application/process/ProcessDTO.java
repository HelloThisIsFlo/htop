package com.floriankempenich.htop.domain.application.process;


public class ProcessDTO {

    public final String name;
    public final Float percentCPU;

    public ProcessDTO(String name, Float percentCPU) {
        this.name = name;
        this.percentCPU = percentCPU;
    }
}

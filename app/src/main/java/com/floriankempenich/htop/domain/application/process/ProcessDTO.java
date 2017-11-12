package com.floriankempenich.htop.domain.application.process;


import java.util.Objects;

public class ProcessDTO {

    public final String name;
    public final Float percentCPU;

    public ProcessDTO(String name, Float percentCPU) {
        this.name = name;
        this.percentCPU = percentCPU;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessDTO that = (ProcessDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(percentCPU, that.percentCPU);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, percentCPU);
    }
}

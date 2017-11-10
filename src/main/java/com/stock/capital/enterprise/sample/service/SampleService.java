package com.stock.capital.enterprise.sample.service;

import com.stock.capital.enterprise.sample.dto.SampleDto;

public interface SampleService {

    public void test(SampleDto dto);

    public void throwEx();

    public SampleDto modify(String sampleId);

    public void save(SampleDto dto);

    public void del(String sampleId);

}

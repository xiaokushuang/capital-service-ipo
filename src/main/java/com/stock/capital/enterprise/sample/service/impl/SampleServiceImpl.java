package com.stock.capital.enterprise.sample.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.stock.capital.enterprise.common.dao.SampleItemMapper;
import com.stock.capital.enterprise.common.dao.SampleMapper;
import com.stock.capital.enterprise.common.entity.Sample;
import com.stock.capital.enterprise.common.entity.SampleExample;
import com.stock.capital.enterprise.common.entity.SampleItem;
import com.stock.capital.enterprise.common.entity.SampleItemExample;
import com.stock.capital.enterprise.sample.dto.SampleDto;
import com.stock.capital.enterprise.sample.dto.SampleItemDto;
import com.stock.capital.enterprise.sample.service.SampleService;
import com.stock.core.service.BaseService;
import com.stock.core.util.BeanUtil;

@Service
public class SampleServiceImpl extends BaseService implements SampleService {

    @Autowired
    private SampleMapper sampleMapper;

    @Autowired
    private SampleItemMapper sampleItemMapper;

    @Transactional(rollbackFor = { Exception.class })
    public void test(SampleDto dto) {
        SampleExample example = new SampleExample();
        sampleMapper.selectByExample(example);
        this.save(dto);
        this.throwEx();
    }

    public void throwEx() {
        throw new RuntimeException();
    }

    public SampleDto modify(String sampleId) {
        Sample sample = sampleMapper.selectByPrimaryKey(sampleId);
        SampleDto dto = new SampleDto();
        BeanUtil.copy(sample, dto);
        List<SampleItemDto> items = Lists.newArrayList();
        dto.setItems(items);
        SampleItemExample itemExample = new SampleItemExample();
        itemExample.createCriteria().andSampleIdEqualTo(sampleId);
        List<SampleItem> itemList = sampleItemMapper.selectByExample(itemExample);
        SampleItemDto itemDto = null;
        for (SampleItem sampleItem : itemList) {
            itemDto = new SampleItemDto();
            BeanUtil.copy(sampleItem, itemDto);
            items.add(itemDto);
        }
        return dto;
    }

    @Transactional(rollbackFor = { Exception.class })
    public void save(SampleDto dto) {
        Sample entity = new Sample();
        BeanUtil.copy(dto, entity);
        if (StringUtils.isEmpty(entity.getId())) {
            sampleMapper.insert(entity);
            SampleItem item = null;
            if (dto.getItems() != null) {
                for (SampleItemDto itemDto : dto.getItems()) {
                    item = new SampleItem();
                    BeanUtil.copy(itemDto, item);
                    item.setSampleId(entity.getId());
                    sampleItemMapper.insert(item);
                }
            }
            // saveFileFromTemp("0", entity.getId(), dto.getFileId());
        } else {
            sampleMapper.updateByPrimaryKey(entity);
            SampleItemExample itemExample = new SampleItemExample();
            itemExample.createCriteria().andSampleIdEqualTo(entity.getId());
            List<SampleItem> itemList = sampleItemMapper.selectByExample(itemExample);
            List<SampleItemDto> items = dto.getItems();
            SampleItemDto updateItemDto = null;
            boolean isUpdate = false;
            for (SampleItem item : itemList) {
                isUpdate = false;
                updateItemDto = null;
                for (SampleItemDto itemDto : items) {
                    if (item.getId().equals(itemDto.getId())) {
                        isUpdate = true;
                        updateItemDto = itemDto;
                        break;
                    }
                }
                if (isUpdate) {
                    BeanUtil.copy(updateItemDto, item);
                    sampleItemMapper.updateByPrimaryKey(item);
                } else {
                    sampleItemMapper.deleteByPrimaryKey(item.getId());
                }
            }
            SampleItem insertItem = null;
            if (items != null) {
                for (SampleItemDto itemDto : items) {
                    if (StringUtils.isEmpty(itemDto.getId())) {
                        insertItem = new SampleItem();
                        BeanUtil.copy(itemDto, insertItem);
                        insertItem.setSampleId(entity.getId());
                        sampleItemMapper.insert(insertItem);
                    }
                }
            }
        }

    }

    @Transactional(rollbackFor = { Exception.class })
    public void del(String sampleId) {
        sampleMapper.deleteByPrimaryKey(sampleId);
        SampleItemExample itemExample = new SampleItemExample();
        itemExample.createCriteria().andSampleIdEqualTo(sampleId);
        List<SampleItem> itemList = sampleItemMapper.selectByExample(itemExample);
        for (SampleItem item : itemList) {
            sampleItemMapper.deleteByPrimaryKey(item.getId());
        }
    }

}
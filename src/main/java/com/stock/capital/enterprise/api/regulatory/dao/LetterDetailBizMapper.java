/**
 * Copyright 2016. All Rights Reserved.
 *
 * 监管动态  函件分类 函件标注 函件数据
 *
 * 作成日:2016/6/6
 * 作成者:韩巍
 */

package com.stock.capital.enterprise.api.regulatory.dao;

import com.stock.capital.enterprise.api.regulatory.dto.*;
import com.stock.capital.enterprise.common.entity.ViolateManage;

import java.util.List;

public interface LetterDetailBizMapper {
	// 取函件信息
	public LetterDetailDto queryLetterInfo(String letterId);

	// 关联函件
	public List<String> queryRelevantIds(String letterId);

	// 关联规则
	public List<LetterRuleDto> queryRelevantRules(String letterId);

	// 关联案例
	public List<ViolateManage> queryLetterViolates(String letterId);

	// 问题和答案
	public List<LabelAskAnswerDto> getAskAndAnswerByLetterId(String letterId);

	// 获取回函
	public List<String> getLetterReturnByLetterId(String letterId);

	// 获取回函附件
	public List<LabelRtnFileDto> getLabelReturnFileByLetterReturnId(
            String letterId);

	// 获取来函
	public List<LabelFileLetterDto> getLabelFileByLetterId(String letterId);
	
	// 获取函件导言以及结束语
        public List<LabelXmlStructureDto> getLabelLevel56(String letterId);
        
        // 获取函件一级标题以及二级标题
        public List<LabelXmlStructureDto> getLabelLevelTitle(String letterId);
        //获取函件问答内容
        //lanyuxin 2017-09-01
        public String getTextContent(String askAndAnswerId);    

}

package com.stock.capital.enterprise.declare.dto;

import java.io.Serializable;
import java.util.List;

import com.stock.capital.enterprise.common.entity.DeclareIdea;
import com.stock.capital.enterprise.common.entity.MaterialPonderMap;

 
public class NoticeHelperDto implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 9060778985453973484L;

    private String typeId;

    private String typeName;
    /**
     * 披露要点
     */
    private List<String> ponder;
    /**
     * 披露要点List
     */
    private List<MaterialPonderMap> ponderList;

	/**
     * 报批材料
     */
    private List<String> material;
    /**
     * 法律法规
     */
    private List<DecalreRelationLawDto> lawRule;
    /**
     * 自我心得
     */
    private List<DeclareIdea> declareIdeaList;
    /**
     * 人员ID
     */
    private String personId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<String> getPonder() {
        return ponder;
    }

    public void setPonder(List<String> ponder) {
        this.ponder = ponder;
    }

    public List<String> getMaterial() {
        return material;
    }

    public void setMaterial(List<String> material) {
        this.material = material;
    }

    public List<DecalreRelationLawDto> getLawRule() {
        return lawRule;
    }

    public void setLawRule(List<DecalreRelationLawDto> lawRule) {
        this.lawRule = lawRule;
    }

    public List<DeclareIdea> getDeclareIdeaList() {
        return declareIdeaList;
    }

    public void setDeclareIdeaList(List<DeclareIdea> declareIdeaList) {
        this.declareIdeaList = declareIdeaList;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
	public List<MaterialPonderMap> getPonderList() {
		return ponderList;
	}

	public void setPonderList(List<MaterialPonderMap> ponderList) {
		this.ponderList = ponderList;
	}


}

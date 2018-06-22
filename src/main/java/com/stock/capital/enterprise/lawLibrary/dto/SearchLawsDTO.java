package com.stock.capital.enterprise.lawLibrary.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.stock.core.dto.OptionDto;

/**
 * 查询分页基本DTO
 *
 * @author zhenght
 *
 * @param <T>
 */
public class SearchLawsDTO implements Serializable {

    private static final long serialVersionUID = 5472321653620726832L;

    // 当前页
    private int currentPage = 1;
    // 起始行
    private int startRow = 0;
    // 每页显示数量
    private int pageSize = 0;
    // 总条数
    private int totalCount;
    // 总页数
    @SuppressWarnings("unused")
    private int pageCount;

    private boolean havaNextPage;

    private boolean havePrePage;

    private int navigatorSize;

    // ————————————————————————————查询参数
    private String personId;
    private String companyId;
    private String typeId;
    private String classId;
    private String keyNote;
    private String keyTitle;
    private String keyAndTitle;
    private String keyNotTitle;
    private String keyContent;
    private String keyAndContent;
    private String keyNotContent;
    private String lawClass;
    private String lawType;
    // 发文单位
    private String lawSource;
    // 适用范围
    private String lawRange;
    // 颁布时间
    private String publishedDate;
    // 失效时间
    private String invalidDate;
    // 排序根据 1:颁布时间 2：法律位阶 3：法规名称 4：重要性
    private String orderByType = "1";
    // 排序顺序 0：升序 1：降序
    private String orderBySeq = "1";
    // 是否显示失效法规 0：不显示 1：显示
    private String invalidTag = "1";
    
    private String newTagName;

    private List<OptionDto> tagList;
    
    private List<String> lawSourceList;
    
    private List<String> lawRangeList;
    
    private List<String> flagList;
    
    private List<CodeDto> sourceLst;
    
    private List<CodeDto> rangeLst;
    
    private String flag;
    
    private String lawSourceShow;

    private String lawRangeShow;
    
    private String lawSourceName;
    
    private String lawRangeName;
    
    private String invdLawsShow;
    
    private String invdLawsName;
    
    private List<CodeDto> invdLaws;

    // yueh start 需求1833 2017/9.28
    private String orderByOrder ;
    
    private String orderColumn ;
    // ————————————————————————————查询结果
    public int getPage() {
        return (this.currentPage - 1) * pageSize;
    }

    public int getPageCount() {
        int pageCount = 1;
        if (pageSize != 0) {
            pageCount = totalCount / pageSize;
            if (totalCount % pageSize != 0) {
                pageCount++;
            }
        }
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPage() {
        currentPage = currentPage < getPageCount() ? currentPage : getPageCount();
        currentPage = currentPage < 1 ? 1 : currentPage;

        return currentPage;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isHaveNextPage() {
        havaNextPage = false;
        if ((getPageCount() > 1) && (getPageCount() > getCurrentPage())) {
            havaNextPage = true;
        }
        return havaNextPage;
    }

    public boolean isHavePrePage() {
        havePrePage = false;
        if ((getPageCount() > 1) && (currentPage > 1)) {
            havePrePage = true;
        }
        return havePrePage;
    }

    private int getNavigatorIndex(boolean isBegin) {
        int beginNavigatorIndex = getCurrentPage() - navigatorSize / 2;
        int endNavigatorIndex = getCurrentPage() + navigatorSize / 2;
        beginNavigatorIndex = beginNavigatorIndex < 1 ? 1 : beginNavigatorIndex;
        endNavigatorIndex = endNavigatorIndex < getPageCount() ? endNavigatorIndex : getPageCount();
        while ((endNavigatorIndex - beginNavigatorIndex) < navigatorSize
                && (beginNavigatorIndex != 1 || endNavigatorIndex != getPageCount())) {
            if (beginNavigatorIndex > 1) {
                beginNavigatorIndex--;
            } else if (endNavigatorIndex < getPageCount()) {
                endNavigatorIndex++;
            }
        }

        if (isBegin) {
            return beginNavigatorIndex;
        } else {
            return endNavigatorIndex;
        }
    }

    public int getBeginNavigatorIndex() {
        return getNavigatorIndex(true);
    }

    public int getEndNavigatorIndex() {
        return getNavigatorIndex(false);
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    // 查询参数及结果
    public String getKeyContent() {
        return keyContent;
    }

    public void setKeyContent(String keyContent) {
        this.keyContent = keyContent;
    }

    public String getKeyAndContent() {
        return keyAndContent;
    }

    public void setKeyAndContent(String keyAndContent) {
        this.keyAndContent = keyAndContent;
    }

    public String getKeyNotContent() {
        return keyNotContent;
    }

    public void setKeyNotContent(String keyNotContent) {
        this.keyNotContent = keyNotContent;
    }

    public String getLawClass() {
        return lawClass;
    }

    public void setLawClass(String lawClass) {
        this.lawClass = lawClass;
    }

    public String getLawType() {
        return lawType;
    }

    public void setLawType(String lawType) {
        this.lawType = lawType;
    }

    public String getLawSource() {
        return lawSource;
    }

    public void setLawSource(String lawSource) {
        this.lawSource = lawSource;
    }

    public String getLawRange() {
        return lawRange;
    }

    public void setLawRange(String lawRange) {
        this.lawRange = lawRange;
    }

    public String getOrderByType() {
        return orderByType;
    }

    public void setOrderByType(String orderByType) {
        this.orderByType = orderByType;
    }

    public String getOrderBySeq() {
        return orderBySeq;
    }

    public void setOrderBySeq(String orderBySeq) {
        this.orderBySeq = orderBySeq;
    }

    public String getInvalidTag() {
        return invalidTag;
    }

    public void setInvalidTag(String invalidTag) {
        this.invalidTag = invalidTag;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getKeyTitle() {
        return keyTitle;
    }

    public void setKeyTitle(String keyTitle) {
        this.keyTitle = keyTitle;
    }

    public String getKeyAndTitle() {
        return keyAndTitle;
    }

    public void setKeyAndTitle(String keyAndTitle) {
        this.keyAndTitle = keyAndTitle;
    }

    public String getKeyNotTitle() {
        return keyNotTitle;
    }

    public void setKeyNotTitle(String keyNotTitle) {
        this.keyNotTitle = keyNotTitle;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getKeyNote() {
        return keyNote;
    }

    public void setKeyNote(String keyNote) {
        this.keyNote = keyNote;
    }

	public String getNewTagName() {
		return newTagName;
	}

	public void setNewTagName(String newTagName) {
		this.newTagName = newTagName;
	}

	public List<OptionDto> getTagList() {
		return tagList;
	}

	public void setTagList(List<OptionDto> tagList) {
		this.tagList = tagList;
	}

	public List<String> getLawSourceList() {
		return lawSourceList;
	}

	public void setLawSourceList(List<String> lawSourceList) {
		this.lawSourceList = lawSourceList;
	}

	public List<String> getLawRangeList() {
		return lawRangeList;
	}

	public void setLawRangeList(List<String> lawRangeList) {
		this.lawRangeList = lawRangeList;
	}

	public List<CodeDto> getSourceLst() {
		return sourceLst;
	}

	public void setSourceLst(List<CodeDto> sourceLst) {
		this.sourceLst = sourceLst;
	}

	public List<CodeDto> getRangeLst() {
		return rangeLst;
	}

	public void setRangeLst(List<CodeDto> rangeLst) {
		this.rangeLst = rangeLst;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getLawSourceShow() {
		return lawSourceShow;
	}

	public void setLawSourceShow(String lawSourceShow) {
		this.lawSourceShow = lawSourceShow;
	}

	public String getLawRangeShow() {
		return lawRangeShow;
	}

	public void setLawRangeShow(String lawRangeShow) {
		this.lawRangeShow = lawRangeShow;
	}

	public String getLawSourceName() {
		return lawSourceName;
	}

	public void setLawSourceName(String lawSourceName) {
		this.lawSourceName = lawSourceName;
	}

	public String getLawRangeName() {
		return lawRangeName;
	}

	public void setLawRangeName(String lawRangeName) {
		this.lawRangeName = lawRangeName;
	}

	public String getInvdLawsShow() {
		return invdLawsShow;
	}

	public void setInvdLawsShow(String invdLawsShow) {
		this.invdLawsShow = invdLawsShow;
	}

	public List<CodeDto> getInvdLaws() {
		return invdLaws;
	}

	public void setInvdLaws(List<CodeDto> invdLaws) {
		this.invdLaws = invdLaws;
	}

	public String getInvdLawsName() {
		return invdLawsName;
	}

	public void setInvdLawsName(String invdLawsName) {
		this.invdLawsName = invdLawsName;
	}

	public List<String> getFlagList() {
		return flagList;
	}

	public void setFlagList(List<String> flagList) {
		this.flagList = flagList;
	}

    public String getOrderByOrder() {
        return orderByOrder;
    }

    public void setOrderByOrder(String orderByOrder) {
        this.orderByOrder = orderByOrder;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

}

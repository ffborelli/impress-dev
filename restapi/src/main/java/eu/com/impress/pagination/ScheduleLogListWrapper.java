package eu.com.impress.pagination;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.com.impress.model.ScheduleLog;

import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class ScheduleLogListWrapper implements Serializable  {
	
	public static final long serialVersionUID = 1L;
	
	private Integer currentPage;
    private Integer pageSize;
    private Integer totalResults;

    private String sortFields;
    private String sortDirections;
    @XmlElement
    private List<ScheduleLog> list;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public String getSortFields() {
        return sortFields;
    }

    public void setSortFields(String sortFields) {
        this.sortFields = sortFields;
    }

    public String getSortDirections() {
        return sortDirections;
    }

    public void setSortDirections(String sortDirections) {
        this.sortDirections = sortDirections;
    }

    public List<ScheduleLog> getList() {
        return list;
    }

    public void setList(List<ScheduleLog> list) {
        this.list = list;
    }
	
}

package eu.com.impress.pagination;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import eu.com.impress.model.Place;

import java.io.Serializable;
import java.util.List;

/**
 * Wraps all the information needed to paginate a table.
 *
 * @author Fabrizio Borelli
 */
@XmlRootElement
public class PlaceListWrapper implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalResults;

    private String sortFields;
    private String sortDirections;
    @XmlElement
    private List<Place> list;

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

    public List getList() {
        return list;
    }

    public void setList(List<Place> list) {
        this.list = list;
    }
}

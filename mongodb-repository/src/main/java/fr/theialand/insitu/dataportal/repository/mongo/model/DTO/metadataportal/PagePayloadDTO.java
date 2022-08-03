package fr.theialand.insitu.dataportal.repository.mongo.model.DTO.metadataportal;

public class PagePayloadDTO {
    private FacetFiltersDTO filters;
    private int pageSelected;
    private int pageSize;

    public FacetFiltersDTO getFilters() {
        return filters;
    }

    public void setFilters(FacetFiltersDTO filters) {
        this.filters = filters;
    }

    public int getPageSelected() {
        return pageSelected;
    }

    public void setPageSelected(int pageSelected) {
        this.pageSelected = pageSelected;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

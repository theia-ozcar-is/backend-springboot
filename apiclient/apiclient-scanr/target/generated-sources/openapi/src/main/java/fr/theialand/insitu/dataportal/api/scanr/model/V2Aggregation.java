/*
 * Api Documentation
 * Api Documentation
 *
 * The version of the OpenAPI document: 1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package fr.theialand.insitu.dataportal.api.scanr.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import fr.theialand.insitu.dataportal.api.scanr.model.V2AggregationTermsOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * V2Aggregation
 */
@JsonPropertyOrder({
  V2Aggregation.JSON_PROPERTY_FIELD,
  V2Aggregation.JSON_PROPERTY_FILTERS,
  V2Aggregation.JSON_PROPERTY_FILTERS_MAP_VALUE_TYPE_DUMMY,
  V2Aggregation.JSON_PROPERTY_MIN_DOC_COUNT,
  V2Aggregation.JSON_PROPERTY_ORDER,
  V2Aggregation.JSON_PROPERTY_SIZE
})
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-13T11:24:06.705650+02:00[Europe/Paris]")
public class V2Aggregation {
  public static final String JSON_PROPERTY_FIELD = "field";
  private String field;

  public static final String JSON_PROPERTY_FILTERS = "filters";
  private Map<String, Object> filters = null;

  public static final String JSON_PROPERTY_FILTERS_MAP_VALUE_TYPE_DUMMY = "filtersMapValueTypeDummy";
  private Object filtersMapValueTypeDummy;

  public static final String JSON_PROPERTY_MIN_DOC_COUNT = "min_doc_count";
  private Integer minDocCount;

  public static final String JSON_PROPERTY_ORDER = "order";
  private V2AggregationTermsOrder order;

  public static final String JSON_PROPERTY_SIZE = "size";
  private Integer size;


  public V2Aggregation field(String field) {
    
    this.field = field;
    return this;
  }

   /**
   * Name of the field to aggregate on
   * @return field
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Name of the field to aggregate on")
  @JsonProperty(JSON_PROPERTY_FIELD)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public String getField() {
    return field;
  }


  public void setField(String field) {
    this.field = field;
  }


  public V2Aggregation filters(Map<String, Object> filters) {
    
    this.filters = filters;
    return this;
  }

  public V2Aggregation putFiltersItem(String key, Object filtersItem) {
    if (this.filters == null) {
      this.filters = new HashMap<>();
    }
    this.filters.put(key, filtersItem);
    return this;
  }

   /**
   * In the case of a \&quot;filters\&quot; aggregation : named filters, each defining a bucket or bin, see https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-bucket-filters-aggregation.html)
   * @return filters
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "In the case of a \"filters\" aggregation : named filters, each defining a bucket or bin, see https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-bucket-filters-aggregation.html)")
  @JsonProperty(JSON_PROPERTY_FILTERS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Map<String, Object> getFilters() {
    return filters;
  }


  public void setFilters(Map<String, Object> filters) {
    this.filters = filters;
  }


  public V2Aggregation filtersMapValueTypeDummy(Object filtersMapValueTypeDummy) {
    
    this.filtersMapValueTypeDummy = filtersMapValueTypeDummy;
    return this;
  }

   /**
   * Get filtersMapValueTypeDummy
   * @return filtersMapValueTypeDummy
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_FILTERS_MAP_VALUE_TYPE_DUMMY)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Object getFiltersMapValueTypeDummy() {
    return filtersMapValueTypeDummy;
  }


  public void setFiltersMapValueTypeDummy(Object filtersMapValueTypeDummy) {
    this.filtersMapValueTypeDummy = filtersMapValueTypeDummy;
  }


  public V2Aggregation minDocCount(Integer minDocCount) {
    
    this.minDocCount = minDocCount;
    return this;
  }

   /**
   * In the case of a \&quot;terms\&quot; aggregation (each value defining dynamically a separate bucket or bin), see https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-bucket-terms-aggregation.html)
   * @return minDocCount
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "In the case of a \"terms\" aggregation (each value defining dynamically a separate bucket or bin), see https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-bucket-terms-aggregation.html)")
  @JsonProperty(JSON_PROPERTY_MIN_DOC_COUNT)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getMinDocCount() {
    return minDocCount;
  }


  public void setMinDocCount(Integer minDocCount) {
    this.minDocCount = minDocCount;
  }


  public V2Aggregation order(V2AggregationTermsOrder order) {
    
    this.order = order;
    return this;
  }

   /**
   * Get order
   * @return order
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")
  @JsonProperty(JSON_PROPERTY_ORDER)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public V2AggregationTermsOrder getOrder() {
    return order;
  }


  public void setOrder(V2AggregationTermsOrder order) {
    this.order = order;
  }


  public V2Aggregation size(Integer size) {
    
    this.size = size;
    return this;
  }

   /**
   * In the case of a \&quot;terms\&quot; aggregation (each value defining dynamically a separate bucket or bin), see https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-bucket-terms-aggregation.html)
   * @return size
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "In the case of a \"terms\" aggregation (each value defining dynamically a separate bucket or bin), see https://www.elastic.co/guide/en/elasticsearch/reference/current/search-aggregations-bucket-terms-aggregation.html)")
  @JsonProperty(JSON_PROPERTY_SIZE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)

  public Integer getSize() {
    return size;
  }


  public void setSize(Integer size) {
    this.size = size;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2Aggregation v2Aggregation = (V2Aggregation) o;
    return Objects.equals(this.field, v2Aggregation.field) &&
        Objects.equals(this.filters, v2Aggregation.filters) &&
        Objects.equals(this.filtersMapValueTypeDummy, v2Aggregation.filtersMapValueTypeDummy) &&
        Objects.equals(this.minDocCount, v2Aggregation.minDocCount) &&
        Objects.equals(this.order, v2Aggregation.order) &&
        Objects.equals(this.size, v2Aggregation.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(field, filters, filtersMapValueTypeDummy, minDocCount, order, size);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2Aggregation {\n");
    sb.append("    field: ").append(toIndentedString(field)).append("\n");
    sb.append("    filters: ").append(toIndentedString(filters)).append("\n");
    sb.append("    filtersMapValueTypeDummy: ").append(toIndentedString(filtersMapValueTypeDummy)).append("\n");
    sb.append("    minDocCount: ").append(toIndentedString(minDocCount)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}


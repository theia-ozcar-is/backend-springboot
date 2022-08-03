package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.entity;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.enumerations.EnumEventType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "datasetLogs")

public class DatasetLog {

    private String datasetId;

    private EnumEventType eventType;

    private Date date;

    public DatasetLog(String datasetId, Date date, EnumEventType eventType) {
        this.eventType = eventType;
        this.datasetId = datasetId;
        this.date      = date;
    }

    public void setEventType(EnumEventType eventType) {
        this.eventType = eventType;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EnumEventType getEventType() {
        return eventType;
    }

    public Date getDate() {
        return date;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getDatasetId() {
        return datasetId;
    }

}

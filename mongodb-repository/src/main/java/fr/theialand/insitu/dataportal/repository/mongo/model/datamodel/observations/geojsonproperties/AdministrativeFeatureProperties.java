package fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.geojsonproperties;

import org.springframework.data.mongodb.core.mapping.Field;

public class AdministrativeFeatureProperties extends Properties {
    @Field("id")
    private long id;
    private int admin_level;
    private String name, local_name, name_en, parents, admin_level2_parent_name_en;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAdmin_level2_parent_name_en() {
        return admin_level2_parent_name_en;
    }

    public void setAdmin_level2_parent_name_en(String admin_level2_parent_name_en) {
        this.admin_level2_parent_name_en = admin_level2_parent_name_en;
    }

    public int getAdmin_level() {
        return admin_level;
    }

    public void setAdmin_level(int admin_level) {
        this.admin_level = admin_level;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocal_name() {
        return local_name;
    }

    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }
}

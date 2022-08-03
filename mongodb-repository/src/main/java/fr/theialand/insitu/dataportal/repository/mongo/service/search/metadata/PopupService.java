package fr.theialand.insitu.dataportal.repository.mongo.service.search.metadata;

import fr.theialand.insitu.dataportal.repository.mongo.model.view.popup.PopupContent;

import java.util.List;


public interface PopupService {
    public PopupContent getPopupContent(List<String> payload);
}

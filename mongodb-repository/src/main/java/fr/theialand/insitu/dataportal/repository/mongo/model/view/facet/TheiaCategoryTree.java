/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.theialand.insitu.dataportal.repository.mongo.model.view.facet;

import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.I18n;
import fr.theialand.insitu.dataportal.repository.mongo.model.datamodel.observations.observation.TheiaVariable;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author coussotc
 */
public class TheiaCategoryTree{

    private String uri;
    private List<I18n> prefLabel;
    private List<I18n> simplifiedLabel;
    private int count;
    private List<TheiaCategoryTree> narrowers;
    private List<TheiaCategoryTree> broaders;
    private List<TheiaVariable> theiaVariables;

    //Sort in alphabetical order
    private static Comparator<TheiaVariable> variableComparator = (h1, h2) -> {
        I18n label1 = h1.getSimplifiedLabel().stream().filter(i18n -> "en".equals(i18n.getLang())).findFirst().get();
        I18n label2 = h2.getSimplifiedLabel().stream().filter(i18n -> "en".equals(i18n.getLang())).findFirst().get();
        return label1.getText().compareTo(label2.getText());
    };
    private static Comparator<TheiaCategoryTree> theiaCategoryTreeComparator = (h1, h2) -> {
        I18n label1 = h1.getSimplifiedLabel().stream().filter(i18n -> "en".equals(i18n.getLang())).findFirst().get();
        I18n label2 = h2.getSimplifiedLabel().stream().filter(i18n -> "en".equals(i18n.getLang())).findFirst().get();
        return label1.getText().compareTo(label2.getText());
    };

    public TheiaCategoryTree() {
    }

    public static TheiaCategoryTree withNarrowers(String uri, List<I18n> simplifiedLabel, Set<TheiaCategoryTree> narrowers, int count) {
        TheiaCategoryTree hierarchy = new TheiaCategoryTree();
        hierarchy.setUri(uri);
        hierarchy.setSimplifiedLabel(simplifiedLabel);
        hierarchy.setCount(count);
        hierarchy.setNarrowers(narrowers.stream().sorted(theiaCategoryTreeComparator).collect(Collectors.toList()));
        return hierarchy;
    }

    public static TheiaCategoryTree withBroaders(String uri, List<I18n> simplifiedLabel, Set<TheiaCategoryTree> broaders, int count) {
        TheiaCategoryTree hierarchy = new TheiaCategoryTree();
        hierarchy.setUri(uri);
        hierarchy.setSimplifiedLabel(simplifiedLabel);
        hierarchy.setCount(count);
        hierarchy.setBroaders(broaders.stream().sorted(theiaCategoryTreeComparator).collect(Collectors.toList()));
        return hierarchy;
    }
    
//        public static TheiaCategoryTree withBroadersAndNarrowers(String uri, List<I18n> prefLabel, Set<TheiaCategoryTree> narrowers, Set<TheiaCategoryTree> broaders, int count) {
//        TheiaCategoryTree hierarchy = new TheiaCategoryTree();
//        hierarchy.setUri(uri);;
//        hierarchy.setPrefLabel(prefLabel);
//        hierarchy.setCount(count);
//        hierarchy.setNarrowers(narrowers.stream().sorted((object1, object2) -> object1.getUri().compareTo(object2.getUri())).collect(Collectors.toList()));
//        hierarchy.setBroaders(broaders.stream().sorted((object1, object2) -> object1.getUri().compareTo(object2.getUri())).collect(Collectors.toList()));
//        return hierarchy;
//    }

    public static TheiaCategoryTree withTheiaVariables(String uri, List<I18n> simplifiedLabel, Set<TheiaVariable> theiaVariables, int count) {
        TheiaCategoryTree hierarchy = new TheiaCategoryTree();
        hierarchy.setUri(uri);
        hierarchy.setSimplifiedLabel(simplifiedLabel);
        hierarchy.setCount(count);

        //Only the simplifiedLabel information is necessary for facets. if uri is null, uri is not used in hashcode() method and is necessary for
        //set collection
        Set<TheiaVariable> tmpSet = new HashSet<>();
        theiaVariables.forEach(s -> {
            TheiaVariable v = new TheiaVariable();
            v.setSimplifiedLabel(s.getSimplifiedLabel());
            tmpSet.add(v);
        });

        hierarchy.setTheiaVariables(tmpSet.stream().sorted(variableComparator).collect(Collectors.toList()));
        return hierarchy;
    }



    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<I18n> getPrefLabel() {
        return prefLabel;
    }

    public void setPrefLabel(List<I18n> prefLabel) {
        this.prefLabel = prefLabel;
    }

    public List<TheiaCategoryTree> getNarrowers() {
        return narrowers;
    }

    public void setNarrowers(List<TheiaCategoryTree> narrowers) {
        this.narrowers = narrowers;
    }

    public List<TheiaCategoryTree> getBroaders() {
        return broaders;
    }

    public void setBroaders(List<TheiaCategoryTree> broaders) {
        this.broaders = broaders;
    }

    public List<TheiaVariable> getTheiaVariables() {
        return theiaVariables;
    }

    public void setTheiaVariables(List<TheiaVariable> theiaVariables) {
        this.theiaVariables = theiaVariables;
    }

    public List<I18n> getSimplifiedLabel() {
        return simplifiedLabel;
    }

    public void setSimplifiedLabel(List<I18n> simplifiedLabel) {
        this.simplifiedLabel = simplifiedLabel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

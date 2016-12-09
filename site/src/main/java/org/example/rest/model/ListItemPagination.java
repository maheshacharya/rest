package org.example.rest.model;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import org.onehippo.cms7.essentials.components.paging.Pageable;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class ListItemPagination<T> extends Pageable<T> {

    private List<T> items;

    public ListItemPagination() {
        items = new ArrayList<>();
    }

    @XmlAnyElement(lax = true)
    @Override
    public List<T> getItems() {
        return items;
    }

    public void addItem(T item) {
        items.add(item);
    }
}

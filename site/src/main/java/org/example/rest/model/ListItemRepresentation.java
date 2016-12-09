package org.example.rest.model;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import org.example.beans.EventsDocument;

import javax.jcr.RepositoryException;

public class ListItemRepresentation {

    public ListItemRepresentation represent(EventsDocument eventBean) {
        // TODO populate the title and introduction from the eventBean
        return this;
    }
}
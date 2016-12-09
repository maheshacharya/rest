package training.rest.answer._04;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import org.example.beans.EventsDocument;

import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListItemRepresentation {
    private String title;
    private String link;
    private String imageLink;
    private String introduction;

    public ListItemRepresentation represent(EventsDocument eventBean) {
        // TODO populate the title and introduction from the eventBean
        setTitle(eventBean.getTitle());
        setIntroduction(eventBean.getIntroduction());
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}

package training.rest.answer._14;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import java.util.Calendar;

import org.hippoecm.hst.content.beans.ContentNodeBinder;
import org.hippoecm.hst.content.beans.ContentNodeBindingException;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

import javax.jcr.RepositoryException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoHtmlAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoGalleryImageAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement(name = "newsdocument")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "myhippoproject:newsdocument")
@Node(jcrType = "myhippoproject:newsdocument")
public class NewsDocument extends HippoDocument implements ContentNodeBinder {

    private static Logger log = LoggerFactory.getLogger(NewsDocument.class);

    /**
     * The document type of the news document.
     */
    public final static String DOCUMENT_TYPE = "myhippoproject:newsdocument";
    private final static String TITLE = "myhippoproject:title";
    private final static String DATE = "myhippoproject:date";
    private final static String INTRODUCTION = "myhippoproject:introduction";
    private final static String IMAGE = "myhippoproject:image";
    private final static String CONTENT = "myhippoproject:content";
    private final static String LOCATION = "myhippoproject:location";
    private final static String AUTHOR = "myhippoproject:author";
    private final static String SOURCE = "myhippoproject:source";

    private String title;
    private String introduction;
    private Calendar date;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     * Get the title of the document.
     * @return the title
     */
    @XmlElement
    public String getTitle() {
        return (title == null) ? getProperty(TITLE) : title;
    }

    /**
     * Get the date of the document.
     * @return the date
     */
    @XmlElement
    public Calendar getDate() {
        return (date == null) ? getProperty(DATE) : date;
    }

    /**
     * Get the introduction of the document.
     * @return the introduction
     */
    @XmlElement
    public String getIntroduction() {
        return (introduction == null) ? getProperty(INTRODUCTION) : introduction;
    }

    /**
     * Get the image of the document.
     * @return the image
     */
    @XmlJavaTypeAdapter(HippoGalleryImageAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "myhippoproject:image")
    public HippoGalleryImageSet getImage() {
        return getLinkedBean(IMAGE, HippoGalleryImageSet.class);
    }

    /**
     * Get the main content of the document.
     * @return the content
     */
    @XmlJavaTypeAdapter(HippoHtmlAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "myhippoproject:content")
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    /**
     * Get the location of the document.
     * @return the location
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "myhippoproject:location")
    public String getLocation() {
        return getProperty(LOCATION);
    }

    /**
     * Get the author of the document.
     * @return the author
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "myhippoproject:author")
    public String getAuthor() {
        return getProperty(AUTHOR);
    }

    /**
     * Get the source of the document.
     * @return the source
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "myhippoproject:source")
    public String getSource() {
        return getProperty(SOURCE);
    }

    @Override
    public boolean bind(final Object content, final javax.jcr.Node node) throws ContentNodeBindingException {
        if(content instanceof NewsDocument) {
            NewsDocument newsDocument = (NewsDocument) content;
            try {
                node.setProperty(TITLE, newsDocument.getTitle());
                node.setProperty(DATE, newsDocument.getDate());
                node.setProperty(INTRODUCTION, newsDocument.getIntroduction());
            } catch (RepositoryException e) {
                log.error("Unable to bind the content to the JCR Node" + e.getMessage(), e);
                throw new ContentNodeBindingException(e);
            }
            return true;
        }

        return false;
    }
}

package training.rest.answer._11;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import org.example.beans.NewsDocument;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.jaxrs.services.content.AbstractContentResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// TODO add class annotations
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/myhippoproject:newsdocument/")
public class NewsDocumentContentResource extends AbstractContentResource {

    private static Logger log = LoggerFactory.getLogger(NewsDocumentContentResource.class);

    @GET
    @Path("/")
    public NewsDocument getDocumentResource() {
        try {
            // TODO add code to retrieve and return the current content bean
            final NewsDocument newsDocument = getRequestContentBean(RequestContextProvider.get(), NewsDocument.class);
            if (newsDocument == null) {
                throw new NotFoundException();
            }
            return newsDocument;

        } catch (ObjectBeanManagerException e) {
            log.error("Error retrieving request content bean", e);
        }
        return null;
    }

}

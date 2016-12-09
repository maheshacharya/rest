package org.example.rest;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import org.example.beans.EventsDocument;
import org.example.rest.model.ListItemPagination;
import org.example.rest.model.ListItemRepresentation;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.cms7.essentials.components.paging.Pageable;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;
import org.onehippo.cms7.essentials.components.rest.ctx.DefaultRestContext;
import org.onehippo.cms7.essentials.components.rest.ctx.RestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/events/")
public class EventsResource extends BaseRestResource {

    private static Logger log = LoggerFactory.getLogger(EventsResource.class);

    @GET
    @Path("/")
    public Pageable<ListItemRepresentation> index(@Context HttpServletRequest request) {
        return findListItems(new DefaultRestContext(this, request), EventsDocument.class);
    }

    private Pageable<ListItemRepresentation> findListItems(final RestContext context, final Class<? extends HippoBean> clazz) {
        ListItemPagination<ListItemRepresentation> pageable = new ListItemPagination<>();

        final HstRequestContext hstRequestContext = RequestContextProvider.get();
        final HstLinkCreator hstLinkCreator = hstRequestContext.getHstLinkCreator();

        try {
            final HstQuery query = createQuery(context, clazz, BaseRestResource.Subtypes.EXCLUDE);
            final HstQueryResult results = query.execute();

            pageable.setTotal(results.getTotalSize());
            pageable.setPageSize(context.getPageSize());
            pageable.setPageNumber(context.getPage());

            final HippoBeanIterator beans = results.getHippoBeans();
            while (beans.hasNext()) {
                // TODO create a new ListItemRepresentation to represent a bean and add it to the pageable

            }

        } catch (QueryException e) {
            log.error("Error finding beans", e);
        }
/*
        catch (RepositoryException e) {
            log.error("Error building a ListItemRepresentation", e);
        }
*/
        return pageable;
    }

}

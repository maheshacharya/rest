package training.rest.answer._05;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import org.example.beans.EventsDocument;
import org.example.rest.model.ListItemPagination;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageBean;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.model.content.Link;
import org.onehippo.cms7.essentials.components.paging.Pageable;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;
import org.onehippo.cms7.essentials.components.rest.ctx.DefaultRestContext;
import org.onehippo.cms7.essentials.components.rest.ctx.RestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import training.rest.answer._04.ListItemRepresentation;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
                // TODO create a new ListItemRepresentation for every bean and add it to the pageable
                final EventsDocument event = (EventsDocument) beans.nextHippoBean();
                final ListItemRepresentation listItemRepresentation = new ListItemRepresentation().represent(event);

                pageable.addItem(listItemRepresentation);

                // Create a link to the image of the Event and a link to the detail representation
                final HippoGalleryImageBean thumbnail = event.getImage().getThumbnail();
                final HstLink imgLink = hstLinkCreator.create(thumbnail.getNode(), hstRequestContext, "site");
                if(imgLink != null) {
                    listItemRepresentation.setImageLink(imgLink.toUrlForm(hstRequestContext, true));
                }

                final Link restLink = getRestLink(hstRequestContext, event, null);
                listItemRepresentation.setLink(restLink.getHref());
            }

        } catch (QueryException e) {
            log.error("Error finding beans", e);
        }
        return pageable;
    }

    @GET
    @Path("/{itempath: .+}")
    public EventsDocument page(@PathParam("itempath") String itempath) {
        try {
            final HippoFolderBean mountContentBaseBean = getMountContentBaseBean(RequestContextProvider.get());
            EventsDocument event = mountContentBaseBean.getBean("events/" + itempath, EventsDocument.class);
            if (event == null) {
                throw new NotFoundException();
            }
            return event;
        } catch (ObjectBeanManagerException e) {
            log.error("Error retrieving mount content base bean", e);
        }
        return null;
    }


}

package training.rest.answer._03;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.collections.EnumerationUtils;
import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;

@Path("/hello/")
public class HelloResource extends AbstractResource {

    @GET
    public Response helloWorld() {
        return Response.ok().entity("Hello world!").build();
    }

    @GET
    @Path("client/{clientname}")
    public Response helloClient(@PathParam("clientname") String clientName,
                                @HeaderParam("user-agent") String userAgent,
                                @Context HttpServletRequest httpServletRequest) {

        StringBuilder sb = new StringBuilder();
        sb.append("Hello ");
        sb.append(clientName);
        sb.append(", you are using this browser type: ");
        sb.append(userAgent);
        sb.append(", and your browser locales are: ");
        sb.append(StringUtils.join(EnumerationUtils.toList(httpServletRequest.getLocales()), ", "));

        final HstRequestContext hstRequestContext = RequestContextProvider.get();
        sb.append(". This resource uses this pipeline: ");
        sb.append(hstRequestContext.getResolvedMount().getNamedPipeline());

        return Response.ok().entity(sb.toString()).build();
    }

}
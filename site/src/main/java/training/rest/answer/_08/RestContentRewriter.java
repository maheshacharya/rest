package training.rest.answer._08;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.content.rewriter.impl.SimpleContentRewriter;
import org.hippoecm.hst.core.request.HstRequestContext;

import javax.jcr.Node;

public class RestContentRewriter extends SimpleContentRewriter {

    @Override
    protected String rewriteBinaryLink(String binaryLinkReference, Node hippoHtmlNode, HstRequestContext requestContext, Mount targetMount) {
        return super.rewriteBinaryLink(binaryLinkReference, hippoHtmlNode, requestContext, requestContext.getMount("site"));
    }
}

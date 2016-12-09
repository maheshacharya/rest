package training.rest.answer._09;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.content.rewriter.impl.SimpleContentRewriter;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

public class RestContentRewriter extends SimpleContentRewriter {

    @Override
    protected String rewriteBinaryLink(String binaryLinkReference, Node hippoHtmlNode, HstRequestContext requestContext, Mount targetMount) {
        return super.rewriteBinaryLink(binaryLinkReference, hippoHtmlNode, requestContext, requestContext.getMount("site"));
    }

    @Override
    protected HstLink createInternalLink(Node referencedNode, HstRequestContext requestContext, Mount targetMount) throws RepositoryException {
        HstLink link = super.createInternalLink(referencedNode,requestContext,targetMount);

        if(referencedNode.getPrimaryNodeType().isNodeType("myhippoproject:newsdocument")) {
            link.setPath("NewsDocument/item/" + link.getPath());
        }

        return link;
    }

}

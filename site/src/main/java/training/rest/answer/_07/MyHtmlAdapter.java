package training.rest.answer._07;
/*
 * Copyright 2016 Hippo B.V. (http://www.onehippo.com)
 * Usage is prohibited except for people attending a training given or authorised by Hippo B.V., and only for that purpose.
 */

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.hst.content.rewriter.ContentRewriter;
import org.hippoecm.hst.content.rewriter.impl.SimpleContentRewriter;
import org.hippoecm.hst.core.request.HstRequestContext;

public class MyHtmlAdapter extends XmlAdapter<String, HippoHtml> {

    @Override
    public String marshal(HippoHtml html) throws Exception {
        if (html == null) {
            return null;
        }
        final HstRequestContext context = RequestContextProvider.get();
        final ContentRewriter<String> contentRewriter = new SimpleContentRewriter();
        // the extra method parameter for the mount is used now
        final String rewrite = contentRewriter.rewrite(html.getContent(), html.getNode(), context, "site");
        return "<![CDATA[" + rewrite + "]]>";
    }

    @Override
    public HippoHtml unmarshal(String representation) throws Exception {
        throw new UnsupportedOperationException("Unmarshalling not implemented.");
    }

}
/*
 * Copyright 2019 Viritin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.firitin.components;

import com.vaadin.flow.component.html.Div;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.markdown4j.Markdown4jProcessor;

/**
 *
 * @author mstahv
 */
/**
 * XSS safe rich text label with either Markdown syntax or raw html (sanitized
 * with Jsoup).
 *
 * By default jsoups Whitelist.relaxed is used for sanitizing. This can be
 * overridden by returning custom whitelist with getWhitelist method.
 */
public class RichText extends Div {

    private static final long serialVersionUID = -6926829115110918731L;

    transient private Whitelist whitelist;
    private String richText;

    public RichText() {
        setWidth("100%");
    }

    public RichText(String content) {
        setWidth("100%");
        setRichText(content);
    }

    public RichText withMarkDown(String markdown) {
        try {
            return setRichText(new Markdown4jProcessor().process(markdown));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public RichText withMarkDown(InputStream markdown) {
        try {
            return setRichText(new Markdown4jProcessor().process(markdown));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public RichText withSafeHtml(String html) {
        return setRichText(html);
    }

    public RichText withSafeHtml(InputStream markdown) {
        try {
            return setRichText(IOUtils.toString(markdown, "UTF-8"));
        } catch (IOException ex) {
            throw new RuntimeException("Input stream coulnd't be read!", ex);
        }
    }

    /**
     * Only replaces all new line characters with &lt;br /&gt;, but no Markdown
     * processing.
     *
     * @param text the text value to be displayed
     * @return the object itself for further configuration
     */
    public RichText withNewLines(String text) {
        return setRichText(text.replaceAll("(\\r|\\n|\\r\\n)+", "<br />"));
    }

    public Whitelist getWhitelist() {
        if (whitelist == null) {
            return Whitelist.relaxed();
        }
        return whitelist;
    }

    /**
     *
     * @param whitelist the whitelist used for sanitizing the rich text content
     * @return the object itself for further configuration
     * @deprecated Whitelist is not serializable. Override getWhitelist instead
     * if you need to support serialiazation
     */
    @Deprecated
    public RichText setWhitelist(Whitelist whitelist) {
        this.whitelist = whitelist;
        return this;
    }

    @Override
    public String getText() {
        return richText;
    }

    public RichText setRichText(String text) {
        this.richText = text;
        getElement().setProperty("innerHTML", Jsoup.clean(richText, getWhitelist()));
        return this;
    }

    public RichText withMarkDownResource(String resourceName) {
        return withMarkDown(getClass().getResourceAsStream(resourceName));
    }

    public RichText withSafeHtmlResource(String resourceName) {
        return withSafeHtml(getClass().getResourceAsStream(resourceName));
    }

    public RichText withContent(String content) {
        return setRichText(content);
    }

}
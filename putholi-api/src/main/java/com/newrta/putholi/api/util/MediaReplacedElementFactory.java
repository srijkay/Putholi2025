package com.newrta.putholi.api.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Element;
import org.xhtmlrenderer.extend.FSImage;
import org.xhtmlrenderer.extend.ReplacedElement;
import org.xhtmlrenderer.extend.ReplacedElementFactory;
import org.xhtmlrenderer.extend.UserAgentCallback;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFSImage;
import org.xhtmlrenderer.pdf.ITextImageElement;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.simple.extend.FormSubmissionListener;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NEWRTA SOLUTIONS
 *
 */
@Slf4j
public class MediaReplacedElementFactory implements ReplacedElementFactory {
    
    private final ReplacedElementFactory superFactory;
    private final String foldername;
    public static final String DATASRC = "data-src";
    /**
     * @param superFactory
     * @param foldername
     */
    public MediaReplacedElementFactory(ReplacedElementFactory superFactory, String foldername) {
        this.superFactory = superFactory;
        this.foldername = foldername;
    }

    /**
     * @return
     */
    public ReplacedElementFactory getSuperFactory() {
        return superFactory;
    }

    /**
     * @return
     */
    public String getFoldername() {
        return foldername;
    }

    /**
     *
     */
    @SuppressWarnings("deprecation")
    @Override
    public ReplacedElement createReplacedElement(LayoutContext layoutContext, BlockBox blockBox,
            UserAgentCallback userAgentCallback, int cssWidth, int cssHeight) {
        Element element = blockBox.getElement();
        if (element == null) {
            return null;
        }
        String nodeName = element.getNodeName();
        String className = element.getAttribute("class");

        if ("div".equals(nodeName) && "media".equals(className)) {
            if (!element.hasAttribute(DATASRC)) {
                log.error("MediaReplacedElementFactory data-src error");
            }
            InputStream qinput = null;
            try {

                qinput = new FileInputStream("/hypercube/evisa/common/rel1_0/barcode/" + foldername + "/"
                        + element.getAttribute(DATASRC));
                final byte[] qbytes = IOUtils.toByteArray(qinput);
                final Image qrimage = Image.getInstance(qbytes);
                final FSImage fsImage = new ITextFSImage(qrimage);
                if (fsImage != null) {
                    if ((cssWidth != -1) || (cssHeight != -1)) {
                        fsImage.scale(cssWidth, cssHeight);
                    }
                    return new ITextImageElement(fsImage);
                }
            } catch (IOException | BadElementException e) {
                log.error("MediaReplacedElementFactory IOException block {}", e);
            } finally {
                IOUtils.closeQuietly(qinput);
            }
        }

        if ("div".equals(nodeName) && "barcode".equals(className)) {
            if (!element.hasAttribute(DATASRC)) {
                throw new RuntimeException(
                        "An element with class `media` is missing a `data-src` attribute indicating the media file.");
            }
            InputStream input = null;
            try {
                input = new FileInputStream(element.getAttribute(DATASRC));
                final byte[] bytes = IOUtils.toByteArray(input);
                final Image image = Image.getInstance(bytes);
                final FSImage fsImage = new ITextFSImage(image);
                if (fsImage != null) {
                    if ((cssWidth != -1) || (cssHeight != -1)) {
                        fsImage.scale(cssWidth, cssHeight);
                    }
                    return new ITextImageElement(fsImage);
                }
            } catch (IOException | BadElementException e) {
                log.error("MediaReplacedElementFactory IOException block {}", e);
            } finally {
                IOUtils.closeQuietly(input);
            }
        }

        return this.superFactory.createReplacedElement(layoutContext, blockBox, userAgentCallback, cssWidth, cssHeight);
    }

    /**
     *
     */
    @Override
    public void reset() {
        superFactory.reset();
    }

    /**
     *
     */
    @Override
    public void remove(Element e) {
        superFactory.remove(e);
    }

    /**
     *
     */
    @Override
    public void setFormSubmissionListener(FormSubmissionListener listener) {
        superFactory.setFormSubmissionListener(listener);
    }

}
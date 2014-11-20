package com.github.spikevlg.habraparser;

import com.google.inject.Inject;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class HabraParser {
    private Grab grab;
    @Inject
    public HabraParser(Grab grab){
        this.grab = grab;
    }

    public HabraItem parse(int articleID) throws Exception{
        String page = grab.go("http://habrahabr.ru/post/" + articleID);
        TagNode tagNode = new HtmlCleaner().clean(page);
        Document doc = new DomSerializer(new CleanerProperties()).createDOM(tagNode);

        XPath xpath = XPathFactory.newInstance().newXPath();
        String postTitle = xpath.evaluate("//h1//span[@class='post_title']/text()", doc);
        boolean isTranslate = (Boolean)xpath.evaluate("count(//h1//span[contains(@class, 'flag_translation')]) > 0", doc
                , XPathConstants.BOOLEAN);
        NodeList habs = (NodeList)xpath.evaluate("//div[@class='hubs']/a/text()", doc, XPathConstants.NODESET);

        System.out.println(postTitle);
        System.out.println(isTranslate);
        for (int i = 0; i < habs.getLength(); i++) {
            System.out.println("hub: " + habs.item(i).getNodeValue());
        }
        return null;
    }
}

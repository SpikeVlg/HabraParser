
package com.github.spikevlg.habraparser.habraparser;

import com.github.spikevlg.habraparser.HabraItem;
import com.github.spikevlg.habraparser.HabraParserException;
import com.github.spikevlg.habraparser.contentprovider.InjectLogger;
import com.google.inject.Inject;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HtmlCleanerHabraParser implements HabraParsable {
    @InjectLogger
    private Logger logger;
    private HtmlCleaner htmlCleaner;
    private DomSerializer domSerializer;
    private XPath xpath;

    @Inject
    public HtmlCleanerHabraParser(HtmlCleaner htmlCleaner, DomSerializer domSerializer, XPath xpath){
        this.htmlCleaner = htmlCleaner;
        this.domSerializer = domSerializer;
        this.xpath = xpath;
    }

    @Override
    public HabraItem parsePost(int postId, String postPage) throws HabraParserException {
        try {
            TagNode tagNode = htmlCleaner.clean(postPage);
            Document doc = domSerializer.createDOM(tagNode);

            HabraItem habraItem = new HabraItem();
            habraItem.setId(postId);
            habraItem.setTitle(xpath.evaluate("//h1//span[@class='post_title']/text()", doc));
            habraItem.setAuthor(xpath.evaluate("//div[@class='author']//a/text()", doc));
            habraItem.setPageViews(parseInteger("//div[@class='pageviews']/text()", doc));
            habraItem.setCountStars(parseInteger("//div[@class='favs_count']/text()", doc));
            habraItem.setTranslate(parseBoolean("count(//h1//span[contains(@class, 'flag_translation')]) > 0", doc));
            habraItem.setCountComments(parseInteger("//span[@id='comments_count']/text()", doc));

            String scoreString = xpath.evaluate("//span[@class='score']/text()", doc);
            try{
                habraItem.setScore(new Double(scoreString.replace("+", "")));
            } catch (NumberFormatException ex){
                habraItem.setScore(0);
            }

            habraItem.setListHubs(parseListString("//div[@class='hubs']/a/text()", doc));
            habraItem.setListTags(parseListString("//ul[@class='tags']/li/a/text()", doc));

            logger.debug(habraItem.toString());
            return habraItem;
        }catch (ParserConfigurationException | XPathExpressionException ex){
            logger.error("parsePost: postId=%d ex=%s", postId, ex.getMessage());
            throw new HabraParserException(ex);
        }
    }



    @Override
    public int getLastPostId(String mainPage) {
        try {
            TagNode tagNode = htmlCleaner.clean(mainPage);
            Document doc = domSerializer.createDOM(tagNode);

            List<Integer> listPostId = new LinkedList<>();
            NodeList nodeList = (NodeList) xpath.evaluate("//div[@class='posts_list']/div/div[contains(@id, 'post_')]/@id"
                    , doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                String tmpPostString = nodeList.item(i).getNodeValue();
                String postIdString = tmpPostString.split("_")[1];
                Integer postId = new Integer(postIdString);
                listPostId.add(postId);
            }
            return Collections.max(listPostId);
        }catch (ParserConfigurationException | XPathExpressionException ex){
            logger.error("getLastPostID: ex=%s", ex.getMessage());
            throw new HabraParserException(ex);
        }
    }

    private List<String> parseListString(String xPath, Document doc) throws XPathExpressionException {
        List<String> listString = new LinkedList<>();
        NodeList nodeList = (NodeList) xpath.evaluate(xPath, doc, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            listString.add(nodeList.item(i).getNodeValue());
        }
        return listString;
    }

    private Double parseDouble(String xPath, Document doc) throws XPathExpressionException{
        return (Double) xpath.evaluate(xPath, doc, XPathConstants.NUMBER);
    }

    private Integer parseInteger(String xPath, Document doc) throws XPathExpressionException {
        return parseDouble(xPath, doc).intValue();
    }

    private Boolean parseBoolean(String xPath, Document doc) throws XPathExpressionException {
        return (Boolean) xpath.evaluate(xPath, doc, XPathConstants.BOOLEAN);
    }
}
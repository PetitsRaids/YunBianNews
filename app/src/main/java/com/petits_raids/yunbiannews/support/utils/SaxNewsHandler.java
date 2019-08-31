package com.petits_raids.yunbiannews.support.utils;

import com.petits_raids.yunbiannews.api.NewsApi;
import com.petits_raids.yunbiannews.data.model.news.News;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxNewsHandler extends DefaultHandler {
    private List<News> newsList = new ArrayList<>();
    private News news;
    private String nodeName;
    private StringBuilder helpBuilder = new StringBuilder();
    private boolean isStart;
    private boolean isFirst = true;
    private int type;
    private int id = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("item")) {
            isStart = true;
        }
        if (!isStart)
            return;
        nodeName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (!isStart)
            return;
        helpBuilder.append(ch, start, length);
        if (!isFirst) {
            news.setTime(helpBuilder.toString().trim());
            helpBuilder.setLength(0);
            isFirst = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!isStart)
            return;
        if ("title".equals(nodeName)) {
            news = new News();
            news.setTitle(helpBuilder.toString().trim());
        } else if ("link".equals(nodeName)) {
            news.setLink(helpBuilder.toString().trim());
            isFirst = false;
        } else if ("description".equals(nodeName)) {
            if (helpBuilder.toString().trim().length() >= 10) {
                news.setDescription(StringUtil.regexReplace("<[^>\\n]*>", helpBuilder.toString(), "").trim());
            }
        }
        helpBuilder.setLength(0);
        if (qName.equals("description")) {
            news.setId(id++);
            news.setType(type);
            newsList.add(news);
        }
    }

    public void setType(int type) {
        this.type = type;
        switch (type) {
            case NewsApi.LOCAL_NEWS:
                id = 0;
                break;
            case NewsApi.WORLD_NEWS:
                id = 300;
                break;
            case NewsApi.POLITICS_NEWS:
                id = 600;
                break;
            case NewsApi.PICTURE_NEWS:
                id = 900;
                break;
            default:
                break;
        }
    }

    public List<News> getNewsList() {
        return newsList;
    }
}

package org.example.springbootrestwebservicee.controller;

import org.example.springbootrestwebservicee.model.News;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/v1")
public class AppController {
    ArrayList<String> validApiKeys = new ArrayList<>(Arrays.asList("key1", "key2", "abckey3", "xyzkey4"));

    @GetMapping("/news")
    public HashMap<String, Object> news(@RequestParam String author, @RequestParam String apiKey) {

        System.out.println("Author: " + author);
        System.out.println("apiKey: " + apiKey);
        
        LinkedHashMap<String, Object> newsMap = new LinkedHashMap<String, Object>();



        newsMap.put("status", "OK");
        newsMap.put("totalResults", 0);

        ArrayList<News> newsList = new ArrayList<News>();
        News news1 = new News("john", "Article1", "Description for Article1");
        News news2 = new News("ron", "Article2", "Description for Article2");
        News news3 = new News("dean", "Article3", "Description for Article3");
        News news4 = new News("andy", "Article4", "Description for Article4");
        News news5 = new News("evan", "Article5", "Description for Article5");
        News news6 = new News("william", "Article6", "Description for Article6");
        News news7 = new News("john", "Article7", "Description for Article7");

        newsList.add(news1);
        newsList.add(news2);
        newsList.add(news3);
        newsList.add(news4);
        newsList.add(news5);
        newsList.add(news6);
        newsList.add(news7);


        if (apiKey.isEmpty()) {

            newsMap.put("status", "error");
            newsMap.put("errorMessage", "apiKey is missing");
            newsMap.put("code", "403");
        } else {
            if (validApiKeys.contains(apiKey)) {
                if (!author.isEmpty()) {
                    List<News> filteredList = newsList.stream().filter(news -> news.getAuthor().equalsIgnoreCase(author)).toList();

                    newsMap.put("totalResults", filteredList.size());
                    newsMap.put("articles", filteredList);
                }
            } else {
                newsMap.put("status", "error");
                newsMap.put("errorMessage", "apiKey is wrong");
                newsMap.put("code", "400");

            }

        }
        return newsMap;

    }
}

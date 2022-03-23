package com.ssafy.myapp.api.service;


import com.ssafy.myapp.api.response.ShowDetailsGetRes;
import com.ssafy.myapp.api.response.ShowListGetRes;

import java.util.List;
import java.util.NoSuchElementException;

public interface ShowService {
    List<ShowListGetRes> getShowAllList();
    List<ShowListGetRes> getShowCategoryAllList(String category);
    ShowDetailsGetRes getShowDetails(Long id) throws NoSuchElementException;
}

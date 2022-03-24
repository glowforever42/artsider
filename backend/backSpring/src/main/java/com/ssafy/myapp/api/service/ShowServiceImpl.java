package com.ssafy.myapp.api.service;


import com.ssafy.myapp.api.response.ArtCenterDetailsGetRes;
import com.ssafy.myapp.api.response.ShowDetailsGetRes;
import com.ssafy.myapp.api.response.ShowListGetRes;
import com.ssafy.myapp.db.entity.ArtCenter;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.repository.ArtCenterRepository;
import com.ssafy.myapp.db.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService{

    private final ShowRepository showRepository;
    private final ArtCenterRepository artCenterRepository;

    // 전체 공연 목록 조회
    @Override
    public List<ShowListGetRes> getShowAllList() {
        return showRepository.findAll().stream().map(ShowListGetRes::new).collect(Collectors.toList());
    }

    // 카테고리별 전체 공연 목록 조회
    @Override
    public List<ShowListGetRes> getShowCategoryAllList(String category) {
        List<ShowListGetRes> showCategoryAllList = new ArrayList<ShowListGetRes>();
        List<Show> ShowAllList = showRepository.findAll();

        for (Show show : ShowAllList) {
            if (show.getCategory().equals(category)) {
                ShowListGetRes showInfo = new ShowListGetRes(show);
                showCategoryAllList.add(showInfo);
            }
        }
        return showCategoryAllList;
    }

    // 공연 상세 조회
    @Override
    public ShowDetailsGetRes getShowDetails(Long id) throws NoSuchElementException {

        Show show = showRepository.findById(id).get();
        ShowDetailsGetRes showInfo = new ShowDetailsGetRes();

        showInfo.setId(show.getId());
        showInfo.setShowId(show.getShowId());
        showInfo.setShowName(show.getShowName());
        showInfo.setStartDate(show.getStartDate());
        showInfo.setEndDate(show.getEndDate());
        showInfo.setOpenRun(show.getOpenRun());
        showInfo.setProducer(show.getProducer());
        showInfo.setAge(show.getAge());
        showInfo.setRuntime(show.getRuntime());
        showInfo.setPrice(show.getPrice());
        showInfo.setPosterPath(show.getPosterPath());
        showInfo.setShowDay(show.getShowDay());
        showInfo.setCategory(show.getCategory());
        showInfo.setArtCenterName(show.getArtCenterName());
        showInfo.setMenRate(show.getMenRate());
        showInfo.setWomenRate(show.getWomenRate());
        return showInfo;
    }

    // 공연장 시설 조회
    @Override
    public ArtCenterDetailsGetRes getArtCenterDetails(String artCenterName) throws NoSuchElementException {

        ArtCenter artCenter = artCenterRepository.findByartCenterName(artCenterName);
        ArtCenterDetailsGetRes artCenterInfo = new ArtCenterDetailsGetRes();

        artCenterInfo.setId(artCenter.getId());
        artCenterInfo.setArtCenterName(artCenter.getArtCenterName());
        artCenterInfo.setArtCenterAddress(artCenter.getArtCenterAddress());
        artCenterInfo.setArtCenterTel(artCenter.getArtCenterTel());
        artCenterInfo.setArtCenterWeb(artCenter.getArtCenterWeb());

        return artCenterInfo;
    }
}

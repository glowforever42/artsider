package com.ssafy.myapp.api.service;


import com.ssafy.myapp.api.response.ShowDetailsGetRes;
import com.ssafy.myapp.api.response.ShowListGetRes;
import com.ssafy.myapp.db.entity.Show;
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
        showInfo.setShow_id(show.getShow_id());
        showInfo.setShow_name(show.getShow_name());
        showInfo.setStart_date(show.getStart_date());
        showInfo.setEnd_date(show.getEnd_date());
        showInfo.setOpenrun(show.getOpenrun());
        showInfo.setProducer(show.getProducer());
        showInfo.setAge(show.getAge());
        showInfo.setRuntime(show.getRuntime());
        showInfo.setPrice(show.getPrice());
        showInfo.setPoster_path(show.getPoster_path());
        showInfo.setShow_day(show.getShow_day());
        showInfo.setCategory(show.getCategory());
        showInfo.setArt_center_name(show.getArt_center_name());
        showInfo.setMen_rate(show.getMen_rate());
        showInfo.setWomen_rate(show.getWomen_rate());
        return showInfo;
    }
}

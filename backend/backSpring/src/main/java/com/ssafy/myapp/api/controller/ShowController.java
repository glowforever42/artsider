package com.ssafy.myapp.api.controller;

import com.ssafy.myapp.api.response.ShowDetailsGetRes;
import com.ssafy.myapp.api.response.ShowListGetRes;
import com.ssafy.myapp.api.service.ShowService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/show")
public class ShowController {

    private final ShowService showService;

    // 전체 공연 목록 조회
    @GetMapping("/all")
    @ApiResponses({    // Api가 붙은 annotation은 swagger와 설정과 관련된 Annotation
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> getShowAllList() {
        List<ShowListGetRes> showAllList = new ArrayList<ShowListGetRes>();
        showAllList = showService.getShowAllList();
        return ResponseEntity.status(200).body(showAllList);
    }


    // 카테고리별 전체 공연 목록 조회
    @GetMapping("/{category}/all")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> getShowCategoryList(@PathVariable String category) {
        List<ShowListGetRes> showCategoryAllList = new ArrayList<ShowListGetRes>();
        showCategoryAllList = showService.getShowCategoryAllList(category);
        return ResponseEntity.status(200).body(showCategoryAllList);
    }


    // 공연 상세 조회
    @GetMapping("/{showId}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 상세 정보 조회 성공"),
            @ApiResponse(code = 401, message = "공연 상세 정보 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<ShowDetailsGetRes> getShowDetails(@PathVariable(value = "showId") Long id) {

        ShowDetailsGetRes showDetails = new ShowDetailsGetRes();

        try {
            showDetails = showService.getShowDetails(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(401).body(showDetails);
        }

        return ResponseEntity.status(200).body(showDetails);
    }
}

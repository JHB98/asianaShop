package com.asiana.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    List<Product> list; // 페이지에서 보여줄 데이터
    int perPage = 4; // 페이지당 보여줄 개수
    //	int totalRecord; // 전체 데이터 ( 전체 레코드 개수 )
    int totalPage; // totalRecord//perPage
    int curPage; // 현재 보여준 페이지 번호
}

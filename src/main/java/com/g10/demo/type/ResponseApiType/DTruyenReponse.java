package com.g10.demo.type.ResponseApiType;

import lombok.Data;

import java.util.List;

@Data
public class DTruyenReponse {
    int status;
    List<DTruyenChapterInfo> chapters;
}

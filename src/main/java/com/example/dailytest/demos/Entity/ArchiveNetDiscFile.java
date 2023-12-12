package com.example.dailytest.demos.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author chenxin
 * @Description
 * @date 2023/12/12 09:47
 */
@ToString
@Data
@Builder
public class ArchiveNetDiscFile {
    private Long id;
    private Long parentId;
}

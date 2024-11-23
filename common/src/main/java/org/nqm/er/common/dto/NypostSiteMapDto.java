package org.nqm.er.common.dto;

import java.util.List;

public record NypostSiteMapDto(int year, List<NypostMonthSiteMapDto> months) {

    public record NypostMonthSiteMapDto(int month, String url) {
    }
}

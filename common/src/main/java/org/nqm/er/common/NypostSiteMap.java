package org.nqm.er.common;

import java.util.List;

public record NypostSiteMap(int year, List<NypostMonthSiteMap> month) {

    public record NypostMonthSiteMap(int month, String url) {
    }
}

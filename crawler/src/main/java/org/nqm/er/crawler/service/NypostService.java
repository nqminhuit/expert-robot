package org.nqm.er.crawler.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.nqm.er.common.dto.NypostSiteMapDto;
import org.nqm.er.common.dto.NypostSiteMapDto.NypostMonthSiteMapDto;
import org.nqm.er.common.utils.ErDateUtils;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NypostService {

    private static Optional<Document> safelyConnect(String url) {
        try {
            return Optional.of(Jsoup.connect(url).get());
        }
        catch (IOException e) {
            Log.errorf("Could NOT connect to '%s' b/c: '%s'", url, e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<List<NypostSiteMapDto>> extractSitemapFromUrl(String url) {
        return safelyConnect(url)
                .map(doc -> doc.select("div.page__content.entry-content div.layout__grid div.layout__item")
                        .stream()
                        .map(el -> {
                            int year = Integer.parseInt(el.select("div.layout__inner h2").text());
                            var months = el.select("div.layout__inner div.date-link a")
                                    .stream()
                                    .map(ell -> new NypostMonthSiteMapDto(
                                            ErDateUtils.getMonth(ell.text()),
                                            ell.attr("href")))
                                    .toList();
                            return new NypostSiteMapDto(year, months);
                        })
                        .toList());
    }
}

package org.nqm.er.processor;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.quarkus.logging.Log;
import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class ArtProcessor {

    @Incoming("art")
    @Blocking
    void getMonthUrlsFromNypostSitemap(String msg) {
        Log.infof("consume %s", msg);
    }
}

package io.quarkus.resteasy.memleak

import io.quarkus.runtime.Startup
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationScoped
@ApplicationPath("/")
@Startup
class App : Application() {
}

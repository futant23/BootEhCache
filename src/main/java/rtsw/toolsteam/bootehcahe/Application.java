/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rtsw.toolsteam.bootehcahe;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rtsw.toolsteam.bootehcahe.domain.Data;

/**
 *
 * @author b1050502
 */
@RestController()
@SpringBootApplication()
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private CacheManager cacheManager = CacheManager.getInstance();

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("run()");

        // the 'name' attribute will be associated with its own cache
        log.info("seeding some cache data ...");
        // create data object and store it
        Data data = new Data("3", "a", "100");
        cacheManager.addCache(data.getName());
        Cache c = cacheManager.getCache(data.getName());
        c.put(new Element(data.getMnemonic(), data));

        // create another data and store it in new cache
        data = new Data("4", "a", "200");
        cacheManager.addCache(data.getName());
        c = cacheManager.getCache(data.getName());
        c.put(new Element(data.getMnemonic(), data));
    }

    @RequestMapping(value = "/data/{name}/{mnemonic}", method = RequestMethod.GET)
    public Data getUserFirstName(@PathVariable String name, @PathVariable String mnemonic) {
       // grab the cacheManager and get the Cache

        Data data = null;
        if (cacheManager.cacheExists(name)) {
            log.info("cache exists for: " + name);
            Cache cache = cacheManager.getCache(name);
            if (cache.isKeyInCache(mnemonic)) {
                Element e = cache.get(mnemonic);
                data = (Data) e.getObjectValue();
                log.info("found data: "+data);
            } 
        }
        // Data is automatically converted to xml via JAXB
       return data;
    }
}

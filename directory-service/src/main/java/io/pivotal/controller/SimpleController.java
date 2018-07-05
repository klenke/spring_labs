package io.pivotal.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
public class SimpleController {

    DBInfo dbinfo;
    public SimpleController(DBInfo dbinfo){
        this.dbinfo = dbinfo;
    }

    @RequestMapping("/dbinfo")
    public DBInfo getInfo(){
        return this.dbinfo;
    }

}

@Component
class DBInfo {
    private String url;

    public DBInfo(DataSource dataSource) throws SQLException {
        this.url = dataSource.getConnection().getMetaData().getURL();
    }

    public String getUrl(){
        return url;
    }
}

package com.istuary.webserviceTemplate.api.dal.DynaSqlProvider;

import org.apache.ibatis.jdbc.SQL;

public class DemoDynaSqlProvider {
    public String findDemosByIdSql(final int id) {
        return new SQL() {
            {
                SELECT("id, demo, user_id, create_date, update_date");
                FROM("demo");
                WHERE("id=" + id);
            }
        }.toString();
    }
}

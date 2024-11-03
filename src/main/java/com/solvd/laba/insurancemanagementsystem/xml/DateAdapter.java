package com.solvd.laba.insurancemanagementsystem.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateAdapter extends XmlAdapter<String, Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date unmarshal(String v) throws Exception {
        if (v == null || v.isEmpty()) {
            return null;
        }
        return (Date) dateFormat.parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        if (v == null) {
            return "";
        }
        return dateFormat.format(v);
    }

}

//package com.noobs.gazonuz.adapters;
//
//import com.google.gson.TypeAdapter;
//import com.google.gson.stream.JsonReader;
//import com.google.gson.stream.JsonToken;
//import com.google.gson.stream.JsonWriter;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Objects;
//
///**
// * Gson TypeAdapter for JSR310 LocalDateTime type
// */
//public class LocalDateTimeTypeAdapter extends TypeAdapter<LocalDateTime> {
//    private DateTimeFormatter formatter;
//
//    public LocalDateTimeTypeAdapter() {
//        this(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//    }
//
//    public LocalDateTimeTypeAdapter(DateTimeFormatter formatter) {
//        this.formatter = formatter;
//    }
//
//    public void setFormat(DateTimeFormatter dateFormat) {
//        this.formatter = dateFormat;
//    }
//
//    @Override
//    public void write(JsonWriter out , LocalDateTime date) throws IOException {
//        if ( date == null ) {
//            out.nullValue();
//        } else {
//            out.value(formatter.format(date));
//        }
//    }
//
//    @Override
//    public LocalDateTime read(JsonReader in) throws IOException {
//        if ( Objects.requireNonNull(in.peek()) == JsonToken.NULL ) {
//            in.nextNull();
//            return null;
//        }
//        String date = in.nextString();
//        return LocalDateTime.parse(date , formatter);
//    }
//}
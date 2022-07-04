package com.example.base;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractDelegate {
    @Inject
    private ObjectMapper mapper;

    public AbstractDelegate() {
    }

    protected <T> T convert(Object source, Class<T> dest) throws IOException {
        T t = null;
        if (source != null) {
            String sourceJson = mapper.writeValueAsString(source);
            t = mapper.readValue(sourceJson, dest);
        }

        return t;
    }

    protected ObjectMapper getMapper() {
        return this.mapper;
    }

    protected <S, T> List<T> convertToList(List<S> sourceList, Class<T> targetType) throws IOException {
        List<T> listModelInfo = new ArrayList();
        Iterator var4 = sourceList.iterator();

        while(var4.hasNext()) {
            S source = (S) var4.next();
            T modelInfo = convert(source, targetType);
            listModelInfo.add(modelInfo);
        }

        return listModelInfo;
    }
}
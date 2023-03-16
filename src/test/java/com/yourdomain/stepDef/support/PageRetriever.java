package com.yourdomain.stepDef.support;

import com.yourdomain.util.ApiUtils;
import com.yourdomain.util.DataStore;

public class PageRetriever {

    private ApiUtils apiUtils;
    private DataStore dataStore;

    public ApiUtils getApiUtils(){
        if (apiUtils == null){
            apiUtils = new ApiUtils();
        }
        return apiUtils;
    }

    public DataStore getDataStore(){
        if (dataStore == null){
            dataStore = new DataStore();
        }
        return dataStore;
    }
}

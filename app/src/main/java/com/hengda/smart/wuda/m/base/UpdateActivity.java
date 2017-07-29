package com.hengda.smart.wuda.m.base;

import com.hengda.frame.update.UpdateService;
import com.hengda.frame.update.Utils.UpdaterUtil;

/**
 * Created by lenovo on 2017/4/7.
 */

public abstract class UpdateActivity extends BasaActivity{

    public void checkUpdata(){
        UpdateService.Builder.create(Contants.APP_KEY,
                Contants.APP_SELECT,
                "1",
                UpdaterUtil.getVersionCode(this),
                UpdaterUtil.getDeviceId(this))
                .build(this);

    }

    public abstract void getUpdata();
    public abstract void getString(String mesage);
}

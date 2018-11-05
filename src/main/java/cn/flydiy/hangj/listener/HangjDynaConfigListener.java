package cn.flydiy.hangj.listener;

import cn.flydiy.core.listener.DynaConfigListener;
import java.util.HashSet;
import java.util.Set;

@org.springframework.stereotype.Component
public class HangjDynaConfigListener extends DynaConfigListener {

    @Override
    protected Set<String> getNoAuthFilterPath() {
        Set<String> pathSet = new HashSet<>();
        return pathSet;
    }
}

package com.gsafety.dawn.community.manage.contract.model.comparator;

import com.gsafety.dawn.community.manage.contract.model.total.EpidemicTotalStatisticModel;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;

/**
 * @create 2020-02-16 22:48
 */
public class CompareConfirmed implements Comparator<EpidemicTotalStatisticModel> {
    @Override
    public int compare(EpidemicTotalStatisticModel o1, EpidemicTotalStatisticModel o2) {
        if (CollectionUtils.isEmpty(o1.getNodeModels()) || CollectionUtils.isEmpty(o1.getNodeModels()))
            return -1;
        return o1.getNodeModels().get(0).getCount() - o2.getNodeModels().get(0).getCount();
    }
}

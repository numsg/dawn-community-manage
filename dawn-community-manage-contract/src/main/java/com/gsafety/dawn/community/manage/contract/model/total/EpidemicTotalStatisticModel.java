package com.gsafety.dawn.community.manage.contract.model.total;

import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;

/**
 * @create 2020-02-16 21:02
 */
public class EpidemicTotalStatisticModel  {

    private String name ;

    private String id;

    private int count;

    private List<EpidemicTotalNodeModel> nodeModels;

    public EpidemicTotalStatisticModel() {
        // 空构造
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<EpidemicTotalNodeModel> getNodeModels() {
        return nodeModels;
    }

    public void setNodeModels(List<EpidemicTotalNodeModel> nodeModels) {
        this.nodeModels = nodeModels;
    }

}

package com.artronics.sdwn.domain.map;


import com.artronics.sdwn.domain.entities.node.Neighbor;
import com.artronics.sdwn.domain.entities.node.SdwnNodeEntity;
import org.springframework.stereotype.Component;

@Component("fixedWeightCalculator")
public class FixedWeightCalculator implements WeightCalculator
{
    private double weight;

    @Override
    public double getWeight(SdwnNodeEntity node, Neighbor neighbor)
    {
        return 107;
    }
}

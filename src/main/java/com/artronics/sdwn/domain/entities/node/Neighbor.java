package com.artronics.sdwn.domain.entities.node;

public interface Neighbor<N extends Node>
{
    N getNode();

    Double getWeight();
}

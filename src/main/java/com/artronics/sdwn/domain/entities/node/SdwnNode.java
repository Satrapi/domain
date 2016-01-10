package com.artronics.sdwn.domain.entities.node;

public class SdwnNode extends SimpleNode
{
    protected Long address;

    public SdwnNode(Long address){
        this.address = address;
    }

    public Long getAddress()
    {
        return address;
    }

    @Override
    public int hashCode()
    {
        int result =17;
        Long add_l = getAddress();

        int add = (int) (add_l ^ (add_l >>>32));

        result+=add;

        return 31*result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof SdwnNode))
            return false;
        if (obj == this)
            return true;

        SdwnNode rhs = (SdwnNode) obj;

        return this.getAddress().equals(rhs.getAddress());
    }

    @Override
    public String toString()
    {
        String s="Node ";

        s+=getId()==null ? "": "id: " +getId();
        s+="add: " +getAddress();

        return s;
    }

}

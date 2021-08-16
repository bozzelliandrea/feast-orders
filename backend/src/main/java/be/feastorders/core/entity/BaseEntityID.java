package be.feastorders.core.entity;

import java.io.Serializable;

public interface BaseEntityID<T extends Serializable> extends Serializable {

    T getID();

    void setID(T id);
}

package Controller;

/*
In order to make sure than every item has some unified commands, that every needs - we have implemented them through an interface.
This allows for a "base" command that the super class Item have overwritten.

 */
public interface ItemAction {

    void showItemInfo();

    void useItem();
}// End

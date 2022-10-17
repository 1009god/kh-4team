package test;


public class ItemDto {
 private int itemNo;
 private String itemName; 
 private String itemType;
 private int itemPrice;
 
public ItemDto() {
	super();
}
public int getItemNo() {
	return itemNo;
}
public void setItemNo(int itemNo) {
	this.itemNo = itemNo;
}
public String getItemName() {
	return itemName;
}
public void setItemName(String itemName) {
	this.itemName = itemName;
}
public String getItemType() {
	return itemType;
}
public void setItemType(String itemType) {
	this.itemType = itemType;
}
public int getItemPrice() {
	return itemPrice;
}
public void setItemPrice(int itemPrice) {
	this.itemPrice = itemPrice;
}

@Override
public String toString() {
	return "ItemDto [itemNo=" + itemNo + ", itemName=" + itemName + ", itemType=" + itemType + ", itemPrice="
			+ itemPrice + "]";
}
 

 
}

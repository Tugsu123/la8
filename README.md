# Агуулахын удирдлагын систем
## Лабораторийн ажил №08 - Java

### Шаардлагын тоймлол

Байгууллага олон агуулахтай. Агуулах бүр няравтай бөгөөд нярав нь дараах үйлдлүүдийг хийнэ:

- **Барааг орлогод хүлээн авах** → Орлогын падаан үйлдэж хэвлэнэ (бараа, тоо, нийлүүлэгч, огноо)
- **Барааг зарлагаар гаргах** → Зарлагын падаан үйлдэж хэвлэнэ (бараа, тоо, хүлээн авагч, огноо)
- **Нөөцийн тайлан харах** → Огноогоор шүүж, бүх буюу сонгосон бараагаар эхний үлдэгдэл, нийт орлого, нийт зарлага, эцсийн үлдэгдлийг харна
- **Тооллого хийх** → Нэг бараагаар бодит тоо хэмжээг оруулна; илүүдэл эсвэл дутагдлыг тооцож тухайн өдрөөр шинэчилнэ

---

### Классуудын тодорхойлолт (Нэр үг, үйл үгийн аргаар)

| Класс | Үүрэг | Хамтрагч классууд |
|---|---|---|
| `Product` | Барааны мэдээлэл хадгалах (id, нэр, нэгж) | InventoryItem, SlipItem |
| `InventoryItem` | Барааны одоогийн үлдэгдлийг хянах | Product, Warehouse |
| `SlipItem` | Падааны нэг мөрийн бараа + тоо | Product, IncomingSlip, OutgoingSlip |
| `IncomingSlip` | Орлогын падаан үйлдэх, хэвлэх | SlipItem, Warehouse, Storekeeper |
| `OutgoingSlip` | Зарлагын падаан үйлдэх, хэвлэх | SlipItem, Warehouse, Storekeeper |
| `Stocktake` | Тооллого хийх, илүүдэл/дутагдал тооцох, бараа шинэчлэх | Product, InventoryItem, Warehouse |
| `InventoryReport` | Нөөцийн тайланг огноогоор бараа тус бүрт гаргах | Warehouse, InventoryItem |
| `Warehouse` | Агуулах — нөөц, падаан, тооллогыг удирдах, тайлан гаргах | Дээрх бүгд |
| `Storekeeper` | Нярав — бүх үйлдлийг гүйцэтгэх | Warehouse, бүх падаан |

---

### CRC Карт

**Product**
Мэдэх: productId, name, unit

**InventoryItem**
Мэдэх: product, quantity, lastUpdated
Хийх: addQuantity, removeQuantity, setQuantity

**SlipItem**
Мэдэх: product, quantity

**IncomingSlip**
Мэдэх: slipId, items, supplierName, receivedDate, warehouseId
Хийх: addItem, print

**OutgoingSlip**
Мэдэх: slipId, items, recipientName, dispatchedDate, warehouseId
Хийх: addItem, print

**Stocktake**
Мэдэх: stocktakeId, product, date, actualQty, systemQty, difference
Хийх: apply, printResult

**InventoryReport**
Мэдэх: warehouseId, fromDate, toDate, бараа тус бүрийн дүн
Хийх: print

**Warehouse**
Мэдэх: warehouseId, location, inventory, incomingSlips, outgoingSlips, stocktakes
Хийх: receiveGoods, dispatchGoods, performStocktake, generateReport

**Storekeeper**
Мэдэх: storekeeperID, name, warehouse
Хийх: receiveGoods, dispatchGoods, viewInventoryReport, performStocktake

---

### Хавтасны бүтэц

```
src/main/java/warehouse/
├── Product.java
├── InventoryItem.java
├── SlipItem.java
├── IncomingSlip.java
├── OutgoingSlip.java
├── Stocktake.java
├── InventoryReport.java
├── Warehouse.java
├── Storekeeper.java
└── Main.java
```

### Ажиллуулах заавар

```bash
# Compile
javac -d out (Get-ChildItem -Recurse -Filter *.java | % { $_.FullName })

# Run
java -cp out warehouse.Main
```

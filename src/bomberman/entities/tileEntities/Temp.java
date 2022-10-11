package bomberman.entities.tileEntities;

import bomberman.managers.GamePlay;

//Một lớp phụ, trước hết phải hiểu, bản đồ sẽ có 2 lớp chồng lên nhau.
//Lớp 1: Lớp cỏ ở bên dưới, không kiểm tra va chạm.
//Lớp 2: Lớp vật thể ở bên trên (Wall, Item, Portal), có kiểm tra va chạm.
//Ở lớp 2, có những ô không có vật thể nào, nếu trỏ đến những ô như thế sẽ
//gặp NullPointerException -> Lớp này được tạo ra để tránh Exception đó.
public class Temp extends TileEntity {
    public Temp(int xUnit, int yUnit, GamePlay gamePlay) {
        super(xUnit, yUnit, gamePlay);
        collision = 0;
        gamePlay.addStillObject(this);
    }
}
